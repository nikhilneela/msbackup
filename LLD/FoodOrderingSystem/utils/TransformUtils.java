package org.FoodOrderingSystem.utils;

import org.FoodOrderingSystem.models.OrderInputItem;
import org.FoodOrderingSystem.models.OrderItem;

import java.util.List;
import java.util.stream.Collectors;

public class TransformUtils {
    public static List<OrderItem> getOrderItems(final List<OrderInputItem> orderInputItems) {
        return orderInputItems.stream().map(TransformUtils::getOrderItem).collect(Collectors.toList());
    }

    public static OrderItem getOrderItem(final OrderInputItem orderInputItem) {
        return new OrderItem(orderInputItem.getItemName(), orderInputItem.getQuantity());
    }
}
