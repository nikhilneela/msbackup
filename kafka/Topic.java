package org.example.kafka;

import lombok.AllArgsConstructor;
import org.example.sqsqueue.SQSQueue;

import java.util.*;

@AllArgsConstructor
public class Topic {
    private final String name;
    private Map<String, SQSQueue> subscriptions;

    public Topic(String name) {
        this.name = name;
        subscriptions = new HashMap<>();
    }

    public List<SQSQueue> getSubscriptions() {
        Collection<SQSQueue> values =  subscriptions.values();
        return values.stream().toList();
    }

    public SQSQueue getSubscription(String subscriptionName) {
        return subscriptions.getOrDefault(subscriptionName, null);
    }

    public void addSubscription(final String subscriptionName, final SQSQueue sqsQueue) {
        this.subscriptions.put(subscriptionName, sqsQueue);
    }
}
