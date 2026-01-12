package org.FoodOrderingSystem.models;

import java.util.UUID;

public class User {
    final private String id;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }
}
