package org.example.stockexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Order {
    private final String id;
    private final String userId;
    private final OrderType orderType;
    private final StockTicker ticker;
    private final int quantity;
    private int fulfilledQuantity;
    private final double price;
    private OrderStatus status;
    private Long acceptedAt;

    public Order(final String userId, final OrderType orderType, final StockTicker ticker, final int quantity, final double price) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.orderType = orderType;
        this.ticker = ticker;
        this.quantity = quantity;
        this.price = price;
        this.acceptedAt = System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public void fulfill() {
        status = OrderStatus.PROCESSED;
    }

    public void fulfillPartially(int quantity) {
        status = OrderStatus.PARTIALLY_PROCESSED;
        this.fulfilledQuantity += quantity;
    }

    public int getQuantity() {
        return quantity - fulfilledQuantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderType=" + orderType +
                ", ticker=" + ticker +
                ", quantity=" + quantity +
                ", fulfilledQuantity=" + fulfilledQuantity +
                ", price=" + price +
                ", status=" + status +
                ", acceptedAt=" + acceptedAt +
                '}';
    }
}
