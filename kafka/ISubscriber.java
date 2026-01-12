package org.example.kafka;

import org.example.sqsqueue.IConsumer;

import java.util.List;

public interface ISubscriber {
    List<IConsumer> getConsumers();
}
