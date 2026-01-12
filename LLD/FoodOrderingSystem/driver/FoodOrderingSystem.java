package org.FoodOrderingSystem.driver;

import org.FoodOrderingSystem.repositories.OrdersRepository;
import org.FoodOrderingSystem.repositories.RestaurantsRepository;
import org.FoodOrderingSystem.services.OrderService;
import org.FoodOrderingSystem.services.RestaurantService;
import org.FoodOrderingSystem.strategy.*;

import java.util.List;

public class FoodOrderingSystem {
    private final OrderService orderService;
    private final RestaurantService restaurantService;
    private final RestaurantsRepository restaurantsRepository;
    private final OrdersRepository ordersRepository;
    private final List<IRestaurantSelectionStrategy> restaurantSelectionStrategies;
    private final List<IRestaurantsFilterStrategy> restaurantsFilterStrategies;

    public FoodOrderingSystem() {
        this.restaurantsRepository = new RestaurantsRepository();
        this.ordersRepository = new OrdersRepository();

        this.restaurantSelectionStrategies = List.of(new HighestRatingSelectionStrategy(), new LowestCostSelectionStrategy());
        this.restaurantsFilterStrategies = List.of(new ServiceableRestaurantsFilterStrategy(this.ordersRepository));
        this.orderService = new OrderService(this.restaurantsRepository, this.ordersRepository, this.restaurantSelectionStrategies, this.restaurantsFilterStrategies);
        this.restaurantService = new RestaurantService(this.restaurantsRepository, new SimpleRatingComputeStrategy());
    }
}
