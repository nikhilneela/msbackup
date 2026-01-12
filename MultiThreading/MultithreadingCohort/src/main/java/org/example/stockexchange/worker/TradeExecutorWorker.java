package org.example.stockexchange.worker;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.stockexchange.model.*;

import java.util.*;

@AllArgsConstructor
public class TradeExecutorWorker implements Runnable {
    private final OrderBook orderBook;
    private final TradeBook tradeBook;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            synchronized (orderBook) {
                Set<Double> allPrices = new HashSet<>(orderBook.getBuys().keySet());
                allPrices.addAll(orderBook.getSells().keySet());

                for (Double price : allPrices) {
                    Iterator<String> buyItr = orderBook.getBuys().getOrDefault(price, new LinkedList<>()).iterator();
                    Iterator<String> sellItr = orderBook.getSells().getOrDefault(price, new LinkedList<>()).iterator();

                    while (buyItr.hasNext() && sellItr.hasNext()) {
                        Order buyOrder = orderBook.getOrder(buyItr.next());
                        Order sellOrder = orderBook.getOrder(sellItr.next());

                        if (buyOrder.getQuantity() == sellOrder.getQuantity()) {
                            buyItr.remove();
                            sellItr.remove();
                        } else if (buyOrder.getQuantity() < sellOrder.getQuantity()) {
                            buyItr.remove();
                        } else {
                            sellItr.remove();
                        }
                        executeTrade(buyOrder, sellOrder);
                    }
                }
                orderBook.wait();
            }
        }
    }

    private void executeTrade(final Order buyOrder, final Order sellOrder) {
        int quantity;
        if (buyOrder.getQuantity() == sellOrder.getQuantity()) {
            orderBook.getOrders().remove(buyOrder.getId());
            orderBook.getOrders().remove(sellOrder.getId());
            quantity = buyOrder.getQuantity();

        } else if (buyOrder.getQuantity() < sellOrder.getQuantity()) {
            orderBook.getOrders().remove(buyOrder.getId());
            quantity = buyOrder.getQuantity();
            sellOrder.fulfillPartially(buyOrder.getQuantity());

        } else {
            orderBook.getOrders().remove(sellOrder.getId());
            quantity = sellOrder.getQuantity();
            buyOrder.fulfillPartially(sellOrder.getQuantity());
        }
        Trade trade = Trade.builder()
                .id(UUID.randomUUID().toString())
                .executedAt(System.currentTimeMillis())
                .price(buyOrder.getPrice())
                .type(TradeType.BUY)
                .ticker(buyOrder.getTicker())
                .quantity(quantity)
                .buyerId(buyOrder.getUserId())
                .sellerId(sellOrder.getUserId())
                .build();
        tradeBook.addTrade(trade);
    }
}
