package org.example;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.Queue;

@AllArgsConstructor
public class ConsumerWorker implements Runnable {
    private final IConsumer consumer;
    private final Queue<IMessage> queue;

    @SneakyThrows
    @Override
    public void run() {
        while(true) {
            IMessage message;
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
