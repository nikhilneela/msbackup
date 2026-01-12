package org.example.ScheduledPayments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Account {
    private String id;
    private double balance;

    public boolean canDebit(double amount) {
        return balance >= amount;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        if (canDebit(amount)) {
            this.balance -= amount;
        }
    }
}
