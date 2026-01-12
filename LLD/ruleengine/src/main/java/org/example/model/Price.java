package org.example.model;

public class Price {
    private CurrencyType type;
    private int value;

    public boolean isLesserThan(Price price) {
        if (price.type != this.type) {
            //log warning
            return false;
        }
        return this.value <= price.value;
    }

    public boolean isZero() {
        return value == 0;
    }
}
