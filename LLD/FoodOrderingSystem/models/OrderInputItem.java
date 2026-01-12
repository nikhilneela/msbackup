package org.FoodOrderingSystem.models;

public class OrderInputItem {
    private final String itemName;
    private final Integer quantity;

    public OrderInputItem(String itemName, Integer quantity) {
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
