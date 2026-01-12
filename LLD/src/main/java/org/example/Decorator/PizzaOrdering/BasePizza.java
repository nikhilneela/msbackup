package org.example.Decorator.PizzaOrdering;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasePizza implements IPizza {
    private final String description;
    private final double price;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
