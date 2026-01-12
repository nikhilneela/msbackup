package org.example.ScheduledPayments.service;

import lombok.AllArgsConstructor;
import org.example.ScheduledPayments.Logger.ILogger;
import org.example.ScheduledPayments.model.Payment;
import org.example.ScheduledPayments.model.PaymentStatus;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.UUID;

@AllArgsConstructor
public class PaymentService {
    private final Queue<Payment> scheduledPayments;
    private final AccountService accountService;
    private final PaymentProcessor processor;
    private final ILogger logger;

    public PaymentService(ILogger logger) {
        this.logger = logger;
        scheduledPayments = new PriorityQueue<>((a, b) -> (int) (a.getTimestampInMillis() - b.getTimestampInMillis()));
        this.accountService = new AccountService();
        processor = new PaymentProcessor(scheduledPayments, accountService, logger);
        logger.logInfo("PaymentProcessor thread started");
        new Thread(processor).start();
    }

    public void schedulePayment(final String accountId, final String targetAccountId, long timestamp, int amount, double cashbackPercent) {
        Payment payment = new Payment(
                UUID.randomUUID().toString(),
                accountId,
                targetAccountId,
                amount,
                timestamp,
                cashbackPercent,
                PaymentStatus.SCHEDULED);

        synchronized (scheduledPayments) {
            scheduledPayments.add(payment);
            scheduledPayments.notifyAll();
            logger.logInfo("Notified payment processor");
        }
        logger.logInfo("ScheduledPayment : " + payment);
    }

    public PaymentStatus getPaymentStatus(final String paymentId) {
        return scheduledPayments.stream().filter(payment -> payment.getId().equals(paymentId)).findFirst().orElseThrow().getStatus();
    }
}
