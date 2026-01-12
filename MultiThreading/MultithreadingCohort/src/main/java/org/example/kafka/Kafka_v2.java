package org.example.kafka;

import lombok.AllArgsConstructor;
import org.example.sqsqueue.IConsumer;
import org.example.sqsqueue.IMessage;
import org.example.sqsqueue.Message;
import org.example.sqsqueue.SQSQueue;

import java.util.*;

@AllArgsConstructor
public class Kafka_v2 {
    //using map instead of List<SQSQueue> to support adding/removing consumers runtime
    private final Map<String, SQSQueue> subscriptions;
    private static Kafka_v2 _instance;
    private Map<String, Topic> topicMap;

    public Kafka_v2() {
        subscriptions = new HashMap<>();
        topicMap = new HashMap<>();
    }

    public static Kafka_v2 getInstance() {
        if (_instance == null) {
            synchronized (Kafka.class) {
                if (_instance == null) {
                    _instance = new Kafka_v2();
                }
            }
        }
        return _instance;
    }

    public void registerSubscriber(final String topicName, final String subscriptionName, final ISubscriber subscriber) {
        if (!topicMap.containsKey(topicName)) {
            return;
        }

        final Topic topic = topicMap.get(topicName);
        SQSQueue queue = topic.getSubscription(subscriptionName);
        if (queue == null) {
            queue = new SQSQueue();
        }
        for(IConsumer consumer : subscriber.getConsumers()) {
            queue.registerConsumer(consumer);
        }
        topic.addSubscription(subscriptionName, queue);
    }

    public void addConsumer(final String topicName, final String subscriptionName, final IConsumer consumer) {
        if (!topicMap.containsKey(topicName)) {
            return;
        }

        final Topic topic = topicMap.get(topicName);
        SQSQueue queue = topic.getSubscription(subscriptionName);
        if (queue == null) {
            throw new RuntimeException("Subscription does not exist, create a subscription before adding consumer");
        }
        queue.registerConsumer(consumer);
    }

    public void createTopic(final String name) {
        topicMap.putIfAbsent(name, new Topic(name));
    }

    public void publish(final String topicName, final IMessage message) {
        if (!topicMap.containsKey(topicName)) {
            return;
        }

        for (SQSQueue queue : topicMap.get(topicName).getSubscriptions()) {
            queue.publish(message);
        }
    }
}
