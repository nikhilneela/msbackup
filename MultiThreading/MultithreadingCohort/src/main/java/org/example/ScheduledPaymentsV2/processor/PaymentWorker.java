package org.example.ScheduledPaymentsV2.processor;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.ScheduledPaymentsV2.logger.ILogger;
import org.example.ScheduledPaymentsV2.model.Account;
import org.example.ScheduledPaymentsV2.model.Payment;
import org.example.ScheduledPaymentsV2.model.PaymentStatus;
import org.example.ScheduledPaymentsV2.service.AccountService;

import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class PaymentWorker implements Runnable {
    private final Queue<Payment> readyToExecutePayments;
    private final AccountService accountService;
    private final ILogger logger;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Payment readyToExecutePayment;
            synchronized (readyToExecutePayments) {
                while (readyToExecutePayments.isEmpty()) {
                    logger.logInfo("No Payments ready to Execute");
                    readyToExecutePayments.wait();
                }
                readyToExecutePayment = readyToExecutePayments.poll();
                logger.logInfo("Payment = " + readyToExecutePayment + " will now be executed");
            }
            executePayment(readyToExecutePayment);
        }
    }

    @SneakyThrows
    private void executePayment(final Payment payment) {
        //take exclusive lock on both accounts
        //if success, process transaction
        //else, release both locks to avoid deadlocks
        Lock sourceAccountLock = accountService.getLock(payment.getSourceAccountId());
        Lock targetAccountLock = accountService.getLock(payment.getTargetAccountId());

        for (int attempt = 0; attempt < 3; attempt++) {
            logger.logInfo("Attempt#" + attempt + " started for payment = " + payment);
            sourceAccountLock.lock();
            try {
                if (!targetAccountLock.tryLock(5, TimeUnit.SECONDS)) {
                    sourceAccountLock.unlock();
                } else {
                    try {
                        //able to acquire locks on both accounts
                        boolean status = processTransaction(payment);
                        if (status) {
                            logger.logInfo("Successfully processed payment = " + payment);
                            payment.setStatus(PaymentStatus.COMPLETED);
                        } else {
                            logger.logInfo("Failed executing payment = " + payment);
                            payment.setStatus(PaymentStatus.FAILED);
                        }
                        return;
                    } finally {
                        targetAccountLock.unlock();
                    }
                }
            } finally {
                sourceAccountLock.unlock();
            }
        }
    }

    private boolean processTransaction(final Payment payment) {
        Account sourceAccount = accountService.getAccount(payment.getSourceAccountId());
        Account targetAccount = accountService.getAccount(payment.getTargetAccountId());

        if (!sourceAccount.canDebit(payment.getAmount())) {
            logger.logInfo("Insufficient funds to execute payment = " + payment);
            return false; //log
        }
        sourceAccount.debit(payment.getAmount());
        targetAccount.credit(payment.getAmount());
        double cashbackAmount = computeCashback(payment.getAmount(), payment.getCashbackPercent()); //can be multiple stratgies to compute cashback, for now let's KISS
        sourceAccount.credit(cashbackAmount);
        return true;
    }

    private double computeCashback(int amount, double cashbackPercent) {
        return amount * cashbackPercent / 100.0;
    }
}
