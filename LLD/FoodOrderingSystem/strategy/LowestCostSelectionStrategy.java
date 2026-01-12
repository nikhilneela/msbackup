package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.*;
import java.util.List;
import java.util.Optional;

public class LowestCostSelectionStrategy implements IRestaurantSelectionStrategy {
    @Override
    public boolean canHandle(RestaurantSelectionStrategies selectionStrategies) {
        return RestaurantSelectionStrategies.LOWEST_COST == selectionStrategies;
    }

    @Override
    public Restaurant selectRestaurant(List<Restaurant> restaurants, List<OrderInputItem> orderInputItems) {
        Optional<Restaurant> selectedRestaurant;

        selectedRestaurant = restaurants.stream().min((r1, r2) -> {
            Integer p1 = r1.computePrice(orderInputItems);
            Integer p2 = r2.computePrice(orderInputItems);
            return p1.compareTo(p2);
        });

        return selectedRestaurant.orElse(null);
    }
}
