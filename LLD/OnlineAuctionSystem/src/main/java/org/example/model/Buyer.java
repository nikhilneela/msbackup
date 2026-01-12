package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Buyer {
    private String id;
    private String name;
    @Setter
    private Budget budget;

    public Buyer(@NonNull final String id, @NonNull final String name, final Value value) {
        this.id = id;
        this.name = name;
        this.budget = new Budget(value == null ? new Value(Integer.MAX_VALUE) : value);
    }

    public void updateBudget(@NonNull final Value value) {
        this.budget.setValue(value);
    }

    public boolean canPlaceBid(@NonNull final Value value) {
        return this.budget == null || this.budget.getValue().isGreaterThanOrEqual(value);
    }
}
