package org.example.sqsqueue;

public abstract class BaseConsumer implements IConsumer {
    public void formatAndLogMessage(String message) {
        System.out.println(Thread.currentThread().getName() + " : " + message);
    }
}
