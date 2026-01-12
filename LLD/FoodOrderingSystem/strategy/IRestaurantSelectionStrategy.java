package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.Order;
import org.FoodOrderingSystem.models.OrderInputItem;
import org.FoodOrderingSystem.models.Restaurant;
import org.FoodOrderingSystem.models.RestaurantSelectionStrategies;

import java.util.List;

public interface IRestaurantSelectionStrategy {
    boolean canHandle(RestaurantSelectionStrategies selectionStrategies);
    Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderInputItem> orderInputItems);
}
