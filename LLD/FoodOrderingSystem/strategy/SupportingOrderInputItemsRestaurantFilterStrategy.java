package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.OrderInputItem;
import org.FoodOrderingSystem.models.Restaurant;

import java.util.List;

public class SupportingOrderInputItemsRestaurantFilterStrategy implements IRestaurantsFilterStrategy {

    final private List<OrderInputItem> orderInputItems;
    public SupportingOrderInputItemsRestaurantFilterStrategy(List<OrderInputItem> orderInputItems) {
        this.orderInputItems = orderInputItems;
    }
    @Override
    public List<Restaurant> applyFilter(List<Restaurant> restaurantsToFilter) {
        return restaurantsToFilter.stream().filter(restaurant -> restaurant.canServiceItems(orderInputItems)).toList();
    }
}
