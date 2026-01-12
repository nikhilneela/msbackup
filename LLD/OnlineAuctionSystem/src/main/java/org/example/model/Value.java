package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Value {
    private int amount;

    public boolean isGreaterThanOrEqual(Value v) {
        return this.amount >= v.getAmount();
    }

    public boolean isLesserThanOrEqual(Value v) {
        return this.amount <= v.getAmount();
    }

    public boolean isLesserThan(Value v) {
        return this.amount < v.getAmount();
    }

    public boolean isBetweenInclusive(Value min, Value max) {
        return this.amount >= min.amount && this.amount <= max.amount;
    }

    @Override
    public String toString() {
        return Integer.toString(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Value value)) return false;
        return amount == value.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
