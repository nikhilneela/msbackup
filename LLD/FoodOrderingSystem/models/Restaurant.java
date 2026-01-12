package org.FoodOrderingSystem.models;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Restaurant {
    private final String id;
    private final String name;
    private final Menu menu;
    private final int maxOrderCapacity;
    private int rating;

    public Restaurant(String id, String name, Menu menu, Integer maxOrderCapacity) {
        this.id = id;
        this.name = name;
        this.menu = menu;
        this.maxOrderCapacity = maxOrderCapacity;
    }

    public Menu getMenu() {
        return menu;
    }

    public int getMaxOrderCapacity() {
        return maxOrderCapacity;
    }

    public Integer getRating() {
        return rating;
    }

    public String getId() {
        return id;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menu.addMenuItem(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        this.menu.updateMenuItem(menuItem);
    }

    public String getName() {
        return name;
    }

    public boolean canServiceItems(List<OrderInputItem> orderInputItems) {
        return orderInputItems.stream().allMatch(orderInputItem -> this.menu.hasItem(orderInputItems));
    }

    public int computePrice(List<OrderInputItem> orderInputItems) {
        Map<String, Integer> itemToPriceMap = this.menu.getMenuItems().stream().collect(Collectors.toMap(MenuItem::getItemName, MenuItem::getItemPrice));
        return orderInputItems.stream().mapToInt(o -> itemToPriceMap.getOrDefault(o.getItemName(), 0) * o.getQuantity()).sum();
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
