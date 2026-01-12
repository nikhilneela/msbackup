package org.example.stockexchange.runner;

import org.example.stockexchange.model.Order;
import org.example.stockexchange.model.OrderType;
import org.example.stockexchange.model.StockTicker;
import org.example.stockexchange.service.StockExchangeService;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Runner that generates matching BUY/SELL order pairs (same ticker + same price)
 * and submits them concurrently so the exchange executes trades.
 *
 * New: supports a configurable maximum number of orders to place across all threads via MAX_ORDERS.
 */
public class StockExchangeRunnerAutomated {

    // Tunables (defaults)
    private static final int PRODUCER_THREADS = 8;       // number of pair-generator threads
    private static final int PAIRS_PER_THREAD = 200;     // how many matching pairs each thread generates (if unlimited)
    private static final int SUBMITTER_POOL_SIZE = 16;   // concurrent submitters to create interleaving
    private static final int MAX_INTER_SUBMIT_DELAY_MS = 50; // small randomized delay to vary ordering

    // New constant: maximum number of individual orders to place across all threads.
    // (An order pair contributes 2 towards this limit.)
    private static final int MAX_ORDERS = 1000;

    public static void main(String[] args) throws InterruptedException {
        final StockExchangeService service = new StockExchangeService();

        // Register trade workers for all tickers (same as your existing startup)
        for (StockTicker ticker : StockTicker.values()) {
            service.registerTradeWorker(ticker);
        }

        System.out.printf("Starting %d generator threads, up to %d total orders (pairs will be created until limit reached).%n",
                PRODUCER_THREADS, MAX_ORDERS);

        ExecutorService pairGenerators = Executors.newFixedThreadPool(PRODUCER_THREADS);
        ExecutorService submitters = Executors.newFixedThreadPool(SUBMITTER_POOL_SIZE);

        // Shared counter to ensure we do not exceed MAX_ORDERS
        final AtomicInteger ordersPlaced = new AtomicInteger(0);
        // Latch to know when generators finished producing scheduling tasks
        final CountDownLatch done = new CountDownLatch(PRODUCER_THREADS);
        final Random rnd = new Random();

        for (int t = 0; t < PRODUCER_THREADS; t++) {
            final int threadIndex = t;
            pairGenerators.submit(() -> {
                try {
                    String buyerBase = "buyer-" + threadIndex;
                    String sellerBase = "seller-" + threadIndex;

                    for (int i = 0; i < PAIRS_PER_THREAD; i++) {
                        // Before creating pair, check if there are slots available for at least one order.
                        if (ordersPlaced.get() >= MAX_ORDERS) {
                            break;
                        }

                        StockTicker ticker = StockTicker.values()[rnd.nextInt(StockTicker.values().length)];
                        double price = Math.round((100.0 + rnd.nextInt(900) + rnd.nextDouble()) * 100.0) / 100.0;

                        Order buy = new Order(buyerBase + "-" + i, OrderType.BUY, ticker, 1 + rnd.nextInt(100), price);
                        Order sell = new Order(sellerBase + "-" + i, OrderType.SELL, ticker, 1 + rnd.nextInt(100), price);

                        // Submit pair but first reserve slots for each individual order using CAS loop
                        // We may reserve 0, 1 or 2 slots depending on remaining capacity.
                        boolean buyReserved = tryReserveOrderSlot(ordersPlaced, MAX_ORDERS);
                        if (buyReserved) {
                            // Randomize ordering between buy/sell and submit via submitters pool
                            boolean buyFirst = rnd.nextBoolean();
                            if (buyFirst) {
                                submitters.submit(() -> safePlace(service, buy));
                                // slight randomized delay
                                sleepRandom(rnd, MAX_INTER_SUBMIT_DELAY_MS);
                                // Attempt to reserve & submit sell
                                boolean sellReserved = tryReserveOrderSlot(ordersPlaced, MAX_ORDERS);
                                if (sellReserved) {
                                    submitters.submit(() -> safePlace(service, sell));
                                }
                            } else {
                                submitters.submit(() -> safePlace(service, buy)); // buy reserved already
                                sleepRandom(rnd, MAX_INTER_SUBMIT_DELAY_MS);
                                boolean sellReserved = tryReserveOrderSlot(ordersPlaced, MAX_ORDERS);
                                if (sellReserved) {
                                    submitters.submit(() -> safePlace(service, sell));
                                }
                            }
                        } else {
                            // Couldn't reserve for buy; try reserving a slot for sell alone (if you want single-sided orders).
                            boolean sellReserved = tryReserveOrderSlot(ordersPlaced, MAX_ORDERS);
                            if (sellReserved) {
                                submitters.submit(() -> safePlace(service, sell));
                            } else {
                                // no capacity for either side -> stop producing
                                break;
                            }
                        }

                        // Occasionally add a slightly larger pause to create staggard arrival patterns
                        if (i % 50 == 0) {
                            try {
                                Thread.sleep(10 + rnd.nextInt(40));
                            } catch (InterruptedException ie) {
                                Thread.currentThread().interrupt();
                                break;
                            }
                        }
                    }
                } finally {
                    done.countDown();
                }
            });
        }

        // Wait for generators to finish scheduling submit tasks (they stop early if the limit is reached)
        done.await();

        // Shutdown generators (they have finished), allow submitters to finish remaining placements
        pairGenerators.shutdown();
        submitters.shutdown();
        if (!submitters.awaitTermination(30, TimeUnit.SECONDS)) {
            submitters.shutdownNow();
        }

        System.out.printf("Finished. Total orders reserved/submitted = %d (limit was %d).%n", ordersPlaced.get(), MAX_ORDERS);
        System.out.println("Runner exiting.");
    }

    /**
     * Attempt to reserve a single order slot by incrementing the shared counter via CAS.
     * Returns true if reservation succeeded (counter incremented), false if limit reached.
     */
    private static boolean tryReserveOrderSlot(AtomicInteger ordersPlaced, int maxOrders) {
        while (true) {
            int current = ordersPlaced.get();
            if (current >= maxOrders) {
                return false;
            }
            if (ordersPlaced.compareAndSet(current, current + 1)) {
                // reserved one slot successfully
                return true;
            }
            // else retry
        }
    }

    private static void safePlace(StockExchangeService service, Order order) {
        try {
            service.placeOrder(order);
        } catch (Exception ex) {
            // Keep failures visible during testing; adapt to your logging framework
            System.err.println("Failed to place order: " + order);
            ex.printStackTrace();
        }
    }

    private static void sleepRandom(Random rnd, int maxMs) {
        try {
            Thread.sleep(rnd.nextInt(maxMs + 1));
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}

