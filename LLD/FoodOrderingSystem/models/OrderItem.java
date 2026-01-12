package org.FoodOrderingSystem.models;

public class OrderItem {
    private final String itemName;
    private final int quantity;

    public OrderItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
