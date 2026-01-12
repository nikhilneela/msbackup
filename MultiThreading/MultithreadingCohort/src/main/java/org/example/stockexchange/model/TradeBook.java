package org.example.stockexchange.model;

import org.example.stockexchange.Logger;

import java.util.*;

public class TradeBook {
    private final List<Trade> trades = Collections.synchronizedList(new ArrayList<>());

    public void addTrade(final Trade trade) {
        System.out.println("Executed Trade : " + trade.toString());
        trades.add(trade);
    }

    public List<Trade> getTrades() {
        synchronized (trades) {
            return Collections.unmodifiableList(trades);
        }
    }
}
