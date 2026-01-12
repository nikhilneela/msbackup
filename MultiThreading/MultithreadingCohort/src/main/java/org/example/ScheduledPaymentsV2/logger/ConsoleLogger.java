package org.example.ScheduledPaymentsV2.logger;

public class ConsoleLogger implements ILogger {
    @Override
    public void logInfo(String message) {
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis() + " : " + message);
    }
}
