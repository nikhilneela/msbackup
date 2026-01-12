package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Budget {
    private Value value;

    public void setValue(@NonNull final Value value) {
        if (this.value.isLesserThan(value)) {
            throw new RuntimeException();
        }
        this.value.setAmount(this.value.getAmount() - value.getAmount());
    }
}
