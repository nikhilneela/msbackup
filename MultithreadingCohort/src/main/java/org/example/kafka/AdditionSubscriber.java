package org.example.kafka;

import lombok.AllArgsConstructor;
import org.example.sqsqueue.IConsumer;

import java.util.List;
@AllArgsConstructor
public class AdditionSubscriber implements ISubscriber {
    private final List<IConsumer> consumers;

    @Override
    public List<IConsumer> getConsumers() {
        return consumers;
    }
}
