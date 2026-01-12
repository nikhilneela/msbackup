package org.example.ScheduledPaymentsV2.processor;

import lombok.SneakyThrows;
import org.example.ScheduledPaymentsV2.logger.ILogger;
import org.example.ScheduledPaymentsV2.model.Payment;
import org.example.ScheduledPaymentsV2.service.AccountService;

import java.util.LinkedList;
import java.util.Queue;

public class PaymentProcessor implements Runnable {
    private final Queue<Payment> scheduledPayments;
    private final Queue<Payment> readyToExecutePayments;
    private final ILogger logger;

    public PaymentProcessor(final Queue<Payment> scheduledPayments, AccountService accountService, int numWorkers, ILogger logger) {
        this.scheduledPayments = scheduledPayments;
        this.readyToExecutePayments = new LinkedList<>();
        this.logger = logger;
        for (int i = 0; i < numWorkers; ++i) {
            Thread workerThread = new Thread(new PaymentWorker(readyToExecutePayments, accountService, logger), "PaymentWorker#" + i);
            workerThread.start();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Payment readyToExecutePayment;
            synchronized (scheduledPayments) {
                Payment closestPayment = scheduledPayments.peek();
                while (closestPayment == null || closestPayment.isInFuture()) {
                    if (closestPayment == null) {
                        logger.logInfo("No payments to be processed");
                        scheduledPayments.wait();
                    } else {
                        long remainingTime = closestPayment.getRemainingTimeInMillis();
                        logger.logInfo("The closest payment is " + remainingTime + " away!");
                        scheduledPayments.wait(remainingTime);
                    }
                }
                readyToExecutePayment = scheduledPayments.poll();
            }
            synchronized (readyToExecutePayments) {
                if (readyToExecutePayment != null) {
                    logger.logInfo("Added payment : " + readyToExecutePayment + " to be executed");
                    readyToExecutePayments.add(readyToExecutePayment);
                    logger.logInfo("Notified worker threads to execute payment");
                    readyToExecutePayments.notifyAll(); //this will notify the worker threads
                }
            }
        }
    }
}
