package org.FoodOrderingSystem.services;

import org.FoodOrderingSystem.strategy.IRestaurantsFilterStrategy;
import org.FoodOrderingSystem.utils.TransformUtils;
import org.FoodOrderingSystem.models.*;
import org.FoodOrderingSystem.repositories.OrdersRepository;
import org.FoodOrderingSystem.repositories.RestaurantsRepository;
import org.FoodOrderingSystem.strategy.IRestaurantSelectionStrategy;

import java.util.List;

public class OrderService {

    private final List<IRestaurantSelectionStrategy> restaurantSelectionStrategies;
    private final RestaurantsRepository restaurantsRepository;
    private final OrdersRepository ordersRepository;
    private final List<IRestaurantsFilterStrategy> restaurantsFilterStrategies;

    public OrderService(RestaurantsRepository restaurantsRepository, OrdersRepository ordersRepository, List<IRestaurantSelectionStrategy> restaurantSelectionStrategies, List<IRestaurantsFilterStrategy> restaurantsFilterStrategies) {
        this.restaurantSelectionStrategies = restaurantSelectionStrategies;
        this.restaurantsRepository = restaurantsRepository;
        this.ordersRepository = ordersRepository;
        this.restaurantsFilterStrategies = restaurantsFilterStrategies;
    }

    public Order placeOrder(List<OrderInputItem> orderInputItems, User user, RestaurantSelectionStrategies restaurantSelectionStrategy) throws Exception {
        List<Restaurant> restaurants = restaurantsRepository.getRestaurants();

        for (IRestaurantsFilterStrategy filterStrategy : restaurantsFilterStrategies) {
            restaurants = filterStrategy.applyFilter(restaurants);
        }

        //remove restaurants that cannot process beyond their capacity
        Restaurant selectedRestaurant = null;
        for (IRestaurantSelectionStrategy strategy : restaurantSelectionStrategies) {
            if (strategy.canHandle(restaurantSelectionStrategy)) {
                selectedRestaurant = strategy.selectRestaurant(restaurants, orderInputItems);
            }
        }

        if (selectedRestaurant == null) {
            throw new Exception("No restaurants available to take the order");
        }

        final Order order = new Order(user.getId(), TransformUtils.getOrderItems(orderInputItems), selectedRestaurant.getId());
        ordersRepository.save(order);
        return order;
    }
}
