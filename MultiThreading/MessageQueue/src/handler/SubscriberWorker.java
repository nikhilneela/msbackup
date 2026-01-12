package handler;

import lombok.NonNull;
import lombok.SneakyThrows;
import model.Message;
import model.Topic;
import model.TopicSubscriber;

import java.util.concurrent.atomic.AtomicInteger;

public class SubscriberWorker implements Runnable {
    private final Topic topic;
    private final TopicSubscriber topicSubscriber;

    public SubscriberWorker(@NonNull final Topic topic, @NonNull final TopicSubscriber topicSubscriber) {
        this.topic = topic;
        this.topicSubscriber = topicSubscriber;
    }

    @SneakyThrows
    @Override
    public void run() {
        try {
            synchronized (topicSubscriber) {
                do {
                    int currentOffset = this.topicSubscriber.getOffset().intValue();
                    while (currentOffset >= topic.getMessages().size()) {
                        this.topicSubscriber.wait();
                    }
                    Message message = this.topic.getMessages().get(currentOffset);
                    topicSubscriber.getSubscriber().consume(message);

                    topicSubscriber.getOffset().compareAndSet(currentOffset, currentOffset + 1);
                } while (true);
            }
        } catch (InterruptedException exception) {
            System.out.println("Received exception : " + exception.getMessage());
        }
    }

    public void wakeUpIfNeed() {
        synchronized (topicSubscriber) {
            topicSubscriber.notify();
        }
    }
}
