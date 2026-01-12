package org.example.ScheduledPayments.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.ScheduledPayments.Logger.ILogger;
import org.example.ScheduledPayments.model.Account;
import org.example.ScheduledPayments.model.Payment;
import org.example.ScheduledPayments.model.PaymentStatus;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class PaymentProcessor implements Runnable {
    private final Queue<Payment> scheduledPayments;
    private final AccountService accountService;
    private final ILogger logger;
    private final int MAX_ATTEMPTS = 3;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Payment eligiblePayment = null;
            synchronized (scheduledPayments) {
                long currentTime = System.currentTimeMillis();
                if (!scheduledPayments.isEmpty() && scheduledPayments.peek().getTimestampInMillis() <= currentTime) {
                    eligiblePayment = scheduledPayments.poll();
                } else {
                    if (scheduledPayments.isEmpty()) {
                        logger.logInfo("No payment found, sleeping");
                        scheduledPayments.wait(); // wait indefinitely, gets woken up when a payment is added into the queue
                        logger.logInfo("PaymentProcessor now woke up from indefinite sleep!");
                    } else {
                        final long waitTime = scheduledPayments.peek().getTimestampInMillis() - currentTime;
                        logger.logInfo("No payment ready to execute now, will sleep for " + waitTime);
                        scheduledPayments.wait(waitTime);
                        logger.logInfo("PaymentProcessor now woke up!");
                    }
                }
            }
            if (eligiblePayment != null) {
                logger.logInfo("Started Processing payment : " + eligiblePayment);
                processPayment(eligiblePayment);
            }
        }
    }

    @SneakyThrows
    private void processPayment(final Payment payment) {
        payment.setStatus(PaymentStatus.EXECUTING);
        //grab lock for sourceAccount and targetAccount
        //if lock grab for targetAccount failed, release sourceAccount lock to prevent deadlock

        Lock sourceLock = accountService.getLock(payment.getSrcAccountId());
        Lock targetLock = accountService.getLock(payment.getTarAccountId());

        for (int numAttempts = 0; numAttempts < MAX_ATTEMPTS && payment.getStatus() == PaymentStatus.EXECUTING; ++numAttempts) {
            logger.logInfo("Attempt #" + numAttempts + " for payment = " + payment);
            sourceLock.lock();
            try {
                if (targetLock.tryLock(5, TimeUnit.SECONDS)) {
                    try {
                        logger.logInfo("Now processing transaction");
                        final Account sourceAccount = this.accountService.getAccount(payment.getSrcAccountId());
                        final Account targetAccount = this.accountService.getAccount(payment.getTarAccountId());
                        if (sourceAccount.canDebit(payment.getAmount())) {
                            logger.logInfo("sourceAccount has sufficient balance and can be debited");
                            sourceAccount.debit(payment.getAmount());
                            targetAccount.credit(payment.getAmount());
                            //cashback
                            sourceAccount.credit(payment.getAmount() * (payment.getCashbackPercent() / 100.0));
                            logger.logInfo("Successfully processed payment = " + payment);
                            payment.setStatus(PaymentStatus.SUCCESS);
                        } else {
                            logger.logInfo("Failed processing payment = " + payment);
                            payment.setStatus(PaymentStatus.FAILED);
                    }
                } finally {
                    targetLock.unlock();
                }
                    }
            } finally {
                sourceLock.unlock();
            }
        }
    }
}
