package org.example.ScheduledPayments.Logger;

public class ConsoleLogger implements ILogger {
    @Override
    public void logInfo(String message) {
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis() + " : " + message);
    }
}
