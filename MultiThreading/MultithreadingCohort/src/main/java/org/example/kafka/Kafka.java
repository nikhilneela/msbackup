package org.example.kafka;

import lombok.AllArgsConstructor;
import org.example.sqsqueue.IConsumer;
import org.example.sqsqueue.Message;
import org.example.sqsqueue.SQSQueue;

import java.util.*;

@AllArgsConstructor
public class Kafka {
    //using map instead of List<SQSQueue> to support adding/removing consumers runtime
    private final Map<String, SQSQueue> subscriptions;
    private static Kafka _instance;

    public Kafka() {
        subscriptions = new HashMap<>();
    }

    public static Kafka getInstance() {
        if (_instance == null) {
            synchronized (Kafka.class) {
                if (_instance == null) {
                    _instance = new Kafka();
                }
            }
        }
        return _instance;
    }

    public void publish(final Message message) {
        for (Map.Entry<String, SQSQueue> entry : subscriptions.entrySet()) {
            entry.getValue().publish(message);
        }
    }

    public void registerSubscriber(final String subscriptionName, final ISubscriber subscriber) {
        SQSQueue queue =  subscriptions.computeIfAbsent(subscriptionName, (key) -> new SQSQueue());
        for (IConsumer consumer : subscriber.getConsumers()) {
            queue.registerConsumer(consumer);
        }
    }

    public void addConsumer(final String subscriptionName, final IConsumer consumer) {
        SQSQueue queue =  subscriptions.computeIfAbsent(subscriptionName, (key) -> new SQSQueue());
        queue.registerConsumer(consumer);
    }
}
