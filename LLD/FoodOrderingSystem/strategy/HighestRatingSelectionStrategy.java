package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.OrderInputItem;
import org.FoodOrderingSystem.models.Restaurant;
import org.FoodOrderingSystem.models.RestaurantSelectionStrategies;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class HighestRatingSelectionStrategy implements IRestaurantSelectionStrategy {
    @Override
    public boolean canHandle(RestaurantSelectionStrategies selectionStrategies) {
        return selectionStrategies == RestaurantSelectionStrategies.HIGHEST_RATING;
    }

    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderInputItem> orderInputItems) {
        Optional<Restaurant> restaurant = restaurants.stream().max(Comparator.comparing(Restaurant::getRating));
        return restaurant.orElse(null);
    }
}
