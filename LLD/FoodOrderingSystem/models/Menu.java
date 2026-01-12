package org.FoodOrderingSystem.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Menu {
    private String id;
    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItems.add(menuItem);
    }

    public void updateMenuItem(MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem =  menuItems.stream().filter(mi -> mi.getId().equals(menuItem.getId())).findFirst();

        optionalMenuItem.ifPresent(item -> {
            int index = menuItems.indexOf(item);
            menuItems.set(index, item);
        });
    }

    public void removeMenuItem(MenuItem menuItem) {
        Optional<MenuItem> optionalMenuItem =  menuItems.stream().filter(mi -> mi.getId().equals(menuItem.getId())).findFirst();
        optionalMenuItem.ifPresent(menuItems::remove);
    }

    public boolean hasItem(List<OrderInputItem> orderInputItems) {
        return orderInputItems
                .stream()
                .allMatch(
                        orderInputItem -> this.menuItems
                                .stream()
                                .anyMatch(
                                        menuItem -> menuItem.getItemName().equals(orderInputItem.getItemName())
                                )
                );
    }
}
