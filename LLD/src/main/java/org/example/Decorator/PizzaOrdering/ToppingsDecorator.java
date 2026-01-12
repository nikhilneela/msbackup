package org.example.Decorator.PizzaOrdering;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ToppingsDecorator extends DecoratedPizza {
    private static final Map<String, Double> prices = Map.of(
            "Base", 8.0,
            "Mushrooms", 1.5,
            "Pepporoni", 3.0
    );

    private final LinkedHashMap<String, Integer> map = new LinkedHashMap<>();

    public ToppingsDecorator(String topping, double price, IPizza inner, int quantity) {
        super(topping, price, inner);

        if (inner instanceof ToppingsDecorator innerToppingsDecorator) {
            map.putAll(innerToppingsDecorator.map);
        }
        map.put(topping, quantity);
    }

    @Override
    public String getDescription() {
        StringBuilder builder = new StringBuilder();

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String toppingName = entry.getKey();
            Integer quantity = entry.getValue();
            if (quantity == 1) {
                builder.append(toppingName);
            } else {
                builder.append(quantity).append("x").append(toppingName);
            }
        }
        return builder.append(super.getDescription()).toString();
    }

    @Override
    public double getPrice() {
        double price = 0.0;
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            String toppingName = entry.getKey();
            Integer quantity = entry.getValue();
            Double toppingPrice = prices.get(toppingName);
            price += toppingPrice*quantity;
        }
        return price + super.getPrice();
    }
}
