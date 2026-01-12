package org.example.ScheduledPaymentsV2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class Payment {
    private String id;
    private String targetAccountId;
    private String sourceAccountId;
    private int amount;
    private long timestamp;
    private double cashbackPercent;
    @Setter
    private PaymentStatus status;

    public boolean isInFuture() {
        return timestamp > System.currentTimeMillis();
    }

    public long getRemainingTimeInMillis() {
        return timestamp - System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "id = " + id + " srcAccount = " + sourceAccountId + " targetAccount = " + targetAccountId + " amount = " + amount + " scheduledAt = " + timestamp + " cashback = " + cashbackPercent;
    }
}
