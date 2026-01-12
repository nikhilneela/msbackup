package org.example.sqsqueue;

public interface IConsumer {
    void consume(IMessage message);
}
