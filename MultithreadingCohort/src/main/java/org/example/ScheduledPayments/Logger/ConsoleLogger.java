package org.example.ScheduledPayments.Logger;

public class ConsoleLogger implements ILogger {
    @Override
    public void logInfo(String message) {
        System.out.println(System.currentTimeMillis() + " : " + message);
    }
}
