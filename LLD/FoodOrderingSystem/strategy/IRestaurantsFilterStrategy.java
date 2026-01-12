package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.Restaurant;

import java.util.List;

public interface IRestaurantsFilterStrategy {
    List<Restaurant> applyFilter(List<Restaurant> restaurantsToFilter);
}
