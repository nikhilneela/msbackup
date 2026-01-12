package org.FoodOrderingSystem.services;

import org.FoodOrderingSystem.models.Menu;
import org.FoodOrderingSystem.models.MenuItem;
import org.FoodOrderingSystem.models.Restaurant;
import org.FoodOrderingSystem.repositories.RestaurantsRepository;
import org.FoodOrderingSystem.strategy.IRatingComputeStrategy;

import java.util.List;
import java.util.UUID;

public class RestaurantService {
    private final RestaurantsRepository restaurantsRepository;
    private final IRatingComputeStrategy ratingComputeStrategy;

    public RestaurantService(RestaurantsRepository restaurantsRepository, IRatingComputeStrategy ratingComputeStrategy) {
        this.restaurantsRepository = restaurantsRepository;
        this.ratingComputeStrategy = ratingComputeStrategy;
    }

    public void onBoardRestaurant(String name, int maxCapacity, Menu menu) {
        Restaurant restaurant = new Restaurant(UUID.randomUUID().toString(), name, menu, maxCapacity);
        this.restaurantsRepository.addRestaurant(restaurant);
    }

    public void addItem(String restaurantId, MenuItem menuItem) {
        this.restaurantsRepository.getRestaurantById(restaurantId).addMenuItem(menuItem);
    }

    public void updateItem(String restaurantId, MenuItem menuItem) {
        this.restaurantsRepository.getRestaurantById(restaurantId).updateMenuItem(menuItem);
    }

    public List<Restaurant> getRestaurants() {
        return this.restaurantsRepository.getRestaurants();
    }

    public Restaurant getRestaurantById(String id) {
        return this.restaurantsRepository.getRestaurantById(id);
    }

    public void updateRating(String restaurantId, int rating) {
        Restaurant restaurant = restaurantsRepository.getRestaurantById(restaurantId);
        int newRating = ratingComputeStrategy.computeRating(restaurant.getRating(), rating);
        restaurant.setRating(newRating);
        restaurantsRepository.updateRestaurant(restaurant);
    }
}
