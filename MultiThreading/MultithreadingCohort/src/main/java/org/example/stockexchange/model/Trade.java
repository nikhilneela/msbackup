package org.example.stockexchange.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor
@Builder
public class Trade {
    private final String id;
    private final TradeType type;
    private final String buyerId;
    private final String sellerId;
    private final StockTicker ticker;
    private final int quantity;
    private final double price;
    private final Long executedAt;

    @Override
    public String toString() {
        return "Trade{" +
                "id='" + id + '\'' +
                ", type=" + type +
                ", buyerId='" + buyerId + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", ticker=" + ticker +
                ", quantity=" + quantity +
                ", price=" + price +
                ", executedAt=" + executedAt +
                '}';
    }
}
