package org.example.stockexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@AllArgsConstructor
public class OrderBook {
    private final StockTicker ticker;
    private final Map<Double, Queue<String>> buys;
    private final Map<Double, Queue<String>> sells;
    private final Map<String, Order> orders;

    public OrderBook(final StockTicker ticker) {
        this.ticker = ticker;
        buys = new ConcurrentHashMap<>();
        sells = new ConcurrentHashMap<>();
        orders = new ConcurrentHashMap<>();
    }

    private void placeBuyOrder(final Order order) {
        orders.put(order.getId(), order);
        Queue<String> buyOrders = buys.computeIfAbsent(order.getPrice(), price -> new LinkedList<>());
        buyOrders.add(order.getId());
    }

    private void placeSellOrder(final Order order) {
        orders.put(order.getId(), order);
        Queue<String> sellOrders = sells.computeIfAbsent(order.getPrice(), price -> new LinkedList<>());
        sellOrders.add(order.getId());
    }

    public void placeOrder(final Order order) {
        if (order.getOrderType() == OrderType.BUY) {
            placeBuyOrder(order);
        } else {
            placeSellOrder(order);
        }
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}
