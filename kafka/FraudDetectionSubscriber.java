package org.example.kafka;

import org.example.sqsqueue.IConsumer;

import java.util.List;

public class FraudDetectionSubscriber implements ISubscriber {
    @Override
    public List<IConsumer> getConsumers() {
        return List.of(new FraudDetectionConsumer());
    }
}
