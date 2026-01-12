package org.example.sqsqueue;

import java.util.LinkedList;

public class SQSQueue {
    private static SQSQueue _instance;
    private final LinkedList<IMessage> queue;

    public SQSQueue() {
        queue = new LinkedList<>();
    }

    public void registerConsumer(IConsumer consumer) {
        new Thread(new ConsumerWorker(queue, consumer)).start();
    }

    public void publish(final IMessage message) {
        synchronized (queue) {
            queue.add(message);
            queue.notifyAll();
        }
    }
}
