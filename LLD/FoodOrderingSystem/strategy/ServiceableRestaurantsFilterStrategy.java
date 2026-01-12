package org.FoodOrderingSystem.strategy;

import org.FoodOrderingSystem.models.Restaurant;
import org.FoodOrderingSystem.repositories.OrdersRepository;

import java.util.List;

public class ServiceableRestaurantsFilterStrategy implements IRestaurantsFilterStrategy {

    private final OrdersRepository ordersRepository;

    public ServiceableRestaurantsFilterStrategy(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<Restaurant> applyFilter(List<Restaurant> restaurantsToFilter) {
        return restaurantsToFilter.stream().filter(restaurant -> ordersRepository.getOngoingOrdersForRestaurant(restaurant).size() < restaurant.getMaxOrderCapacity()).toList();
    }
}
