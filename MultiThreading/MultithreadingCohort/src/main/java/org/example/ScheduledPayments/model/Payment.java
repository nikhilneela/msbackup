package org.example.ScheduledPayments.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class Payment {
    private String id;
    private String srcAccountId;
    private String tarAccountId;
    private int amount;
    private long timestampInMillis;
    private double cashbackPercent;
    @Setter
    private PaymentStatus status;

    @Override
    public String toString() {
        return "id = " + id + " srcAccount = " + srcAccountId + " targetAccount = " + tarAccountId + " amount = " + amount + " scheduledAt = " + timestampInMillis + " cashback = " + cashbackPercent;
    }
}
