package org.example.runners;

import lombok.SneakyThrows;
import org.example.queue.BoundedBlockingQueue;

public class BoundedBlockingQueueRunner {
    public void run() {
        BoundedBlockingQueue blockingQueue = new BoundedBlockingQueue(2);

        new Thread(() -> {
            try {
                blockingQueue.enqueue(10);
                Thread.sleep(5000);
                blockingQueue.enqueue(20);
                Thread.sleep(5000);
                blockingQueue.enqueue(30);
                Thread.sleep(5000);
                blockingQueue.enqueue(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
           blockingQueue.dequeue();
        }).start();
    }
}
