package org.example.stockexchange.service;

import lombok.SneakyThrows;
import org.example.stockexchange.Logger;
import org.example.stockexchange.model.*;
import org.example.stockexchange.worker.TradeExecutorWorker;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class StockExchangeService {

    private final Map<StockTicker, OrderBook> orderBookMap;
    private final TradeBook tradeBook;

    public StockExchangeService() {
        orderBookMap = new ConcurrentHashMap<>();
        tradeBook = new TradeBook();
    }

    @SneakyThrows
    public void placeOrder(final Order order) {
        OrderBook orderBook = orderBookMap.computeIfAbsent(order.getTicker(), OrderBook::new);

        synchronized (orderBook) {
            orderBook.placeOrder(order);
            orderBook.notify();
        }
    }

    public void modifyOrder(final Order order) {

    }

    public void cancelOrder(final String orderId) {

    }

    public OrderStatus getOrderStatus(final String orderId) {
        return null;
    }

    public void registerTradeWorker(final StockTicker ticker) {
        OrderBook orderBook = orderBookMap.computeIfAbsent(ticker, OrderBook::new);
        TradeExecutorWorker tradeExecutorWorker = new TradeExecutorWorker(orderBook, tradeBook);
        new Thread(tradeExecutorWorker).start();
    }

    public List<Trade> listTrades() {
        return tradeBook.getTrades();
    }
}
