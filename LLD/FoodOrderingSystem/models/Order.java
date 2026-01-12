package org.FoodOrderingSystem.models;

import java.util.*;

public class Order {
    private final String id;
    private final String userId;
    private final List<OrderItem> orderItems;
    private final String restaurantId;
    private int price;

    private OrderStatus orderStatus;

    public Order(String userId, List<OrderItem> orderItems, String restaurantId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.orderItems = new ArrayList<>(orderItems);
        this.restaurantId = restaurantId;
        this.orderStatus = OrderStatus.ACCEPTED;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
