package org.example.sqsqueue;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Queue;

@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    private final Queue<IMessage> queue;
    private final IConsumer consumer;

    @Override
    @SneakyThrows
    public void run() {
        IMessage message;
        while (true) {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    queue.wait();
                }
                message = queue.poll();
            }
            consumer.consume(message);
        }
    }
}
