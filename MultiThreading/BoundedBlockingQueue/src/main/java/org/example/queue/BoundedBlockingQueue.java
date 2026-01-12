package org.example.queue;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class BoundedBlockingQueue {
    private final List<Integer> items;
    private final int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.items = new ArrayList<>();
    }

    @SneakyThrows
    public Integer dequeue() {
        System.out.println("de : entered");
        synchronized (items) {
            while (items.isEmpty()) {
                items.wait();
            }
            Integer dequeuedElement = items.removeFirst();
            System.out.println("de : dequeued element " + dequeuedElement);
            items.notifyAll();
            System.out.println("de : notifying");
            return dequeuedElement;
        }
    }

    @SneakyThrows
    public void enqueue(Integer item) {
        System.out.println("en : entered with " + item);
        synchronized (items) {
            while ((items.size() == capacity)) {
                items.wait();
            }
            System.out.println("en : added " + item);
            items.add(item);
            System.out.println("en : notifying");
            items.notifyAll();
        }
    }
}
