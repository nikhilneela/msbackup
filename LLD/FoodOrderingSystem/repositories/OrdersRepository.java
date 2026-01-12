package org.FoodOrderingSystem.repositories;

import org.FoodOrderingSystem.dao.OrderDao;
import org.FoodOrderingSystem.models.Order;
import org.FoodOrderingSystem.models.OrderStatus;
import org.FoodOrderingSystem.models.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class OrdersRepository {
    private final List<Order> orders;

    public OrdersRepository() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOngoingOrdersForRestaurant(Restaurant restaurant) {
        return orders.stream().filter(order -> OrderStatus.ACCEPTED == order.getOrderStatus() && order.getRestaurantId().equals(restaurant.getId())).toList();
    }

    public void save(Order order) {
        orders.add(order);
    }
}
