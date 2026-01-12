package org.FoodOrderingSystem.repositories;

import org.FoodOrderingSystem.models.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantsRepository {
    private final Map<String, Restaurant> restaurants;

    public RestaurantsRepository() {
        restaurants = new HashMap<>();
    }

    public List<Restaurant> getRestaurants() {
        return new ArrayList<>(restaurants.values());
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.put(restaurant.getId(), restaurant);
    }

    public void updateRestaurant(Restaurant restaurant) {
        this.restaurants.put(restaurant.getId(), restaurant);
    }

    public Restaurant getRestaurantById(String id) {
        return this.restaurants.getOrDefault(id, null);
    }
}
