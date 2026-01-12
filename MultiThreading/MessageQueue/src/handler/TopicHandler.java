package handler;

import lombok.NonNull;
import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;

public class TopicHandler {
    private final Topic topic;
    //assign one worker for each subscribers, they will work for the subscriber
    //store worker to subscriber mapping in the Map
    private Map<String, SubscriberWorker> subscriberWorkers;

    public TopicHandler(Topic topic) {
        this.topic = topic;
        subscriberWorkers = new HashMap<>();
    }

    public void publish() {
        for (TopicSubscriber subscriber : topic.getSubscribers()) {
            startWorkerForSubscriber(subscriber);
        }
    }

    public void startWorkerForSubscriber(@NonNull final TopicSubscriber topicSubscriber) {
        final String subscriberId = topicSubscriber.getSubscriber().getId();
        if (!subscriberWorkers.containsKey(subscriberId)) {
            final SubscriberWorker subscriberWorker = new SubscriberWorker(this.topic, topicSubscriber);
            subscriberWorkers.put(subscriberId, subscriberWorker);
            new Thread(subscriberWorker).start();
        }
        final SubscriberWorker subscriberWorker = subscriberWorkers.get(subscriberId);
        subscriberWorker.wakeUpIfNeed();
    }
}
