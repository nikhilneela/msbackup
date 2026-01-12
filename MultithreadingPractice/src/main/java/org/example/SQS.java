package org.example;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class SQS {
    private static volatile SQS _instance;
    private final Queue<IMessage> queue;

    private SQS() {
        queue = new LinkedList<>();
    }

    //threadsafe singleton
    public static SQS getInstance() {
        if (_instance != null) {
            return _instance;
        }

        synchronized (SQS.class) {
            if (_instance == null) {
                _instance = new SQS();
            }
        }
        return _instance;
    }

    public static SQS getInstance_v2() {
        if (_instance == null) {
            synchronized (SQS.class) {
                if (_instance == null) {
                    _instance = new SQS();
                }
            }
        }
        return _instance;
    }

    public void send(IMessage message) {
        synchronized (queue) {
            queue.add(message);
            queue.notifyAll(); //wake up all consumers
        }
    }

    public void registerConsumer(IConsumer consumer) {
        new Thread(new ConsumerWorker(consumer, queue)).start();
    }
}
