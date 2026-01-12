package org.example.ScheduledPaymentsV2.service;

import org.example.ScheduledPaymentsV2.logger.ConsoleLogger;
import org.example.ScheduledPaymentsV2.model.Payment;
import org.example.ScheduledPaymentsV2.model.PaymentStatus;
import org.example.ScheduledPaymentsV2.processor.PaymentProcessor;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;

public class PaymentService {
    private final Queue<Payment> scheduledPayments;

    public PaymentService(final AccountService accountService) {
        scheduledPayments = new PriorityQueue<>((a, b) -> (int) (a.getTimestamp() - b.getTimestamp()));
        Thread processorThread = new Thread(new PaymentProcessor(scheduledPayments, accountService, 5, new ConsoleLogger()), "PaymentProcessor");
        processorThread.start();
    }

    public void schedulePayment(final String sourceAccountId, final String targetAccountId, final int amount,
                                final long scheduledAt, final double cashbackPercent) {
        synchronized (scheduledPayments) {
            scheduledPayments.add(new Payment(UUID.randomUUID().toString(), targetAccountId, sourceAccountId, amount, scheduledAt, cashbackPercent, PaymentStatus.PENDING));
            scheduledPayments.notifyAll();
        }
    }
}
