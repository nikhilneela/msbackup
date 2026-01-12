package org.FoodOrderingSystem.models;

import java.util.UUID;

public class MenuItem {
    private final String id;
    private final String itemName;
    private final Integer itemPrice;

    public MenuItem(String itemName, Integer price) {
        this.id = UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemPrice = price;
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }
}
