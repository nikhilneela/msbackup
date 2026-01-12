package org.example.Decorator.PizzaOrdering;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class DecoratedPizza implements IPizza {
    private final String topping;
    private final double price;
    private final IPizza inner;

    @Override
    public double getPrice() {
        return inner.getPrice() + this.price;
    }

    public String getDescription() {
        return inner.getDescription() + " " + this.topping;
    }
}
