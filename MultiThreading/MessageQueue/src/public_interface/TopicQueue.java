package public_interface;

import handler.TopicHandler;
import lombok.NonNull;
import model.Message;
import model.Topic;
import model.TopicSubscriber;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicQueue implements IMessageQueue {

    private final Map<String, TopicHandler> topicHandlers;


    public TopicQueue() {
        this.topicHandlers = new HashMap<>();
    }

    @Override
    public Topic createTopic(@NonNull final String topicName) {
        final Topic topic = new Topic(topicName, UUID.randomUUID().toString());
        final TopicHandler topicHandler = new TopicHandler(topic);
        topicHandlers.put(topic.getTopicId(), topicHandler);
        return topic;
    }

    @Override
    public void subscribe(@NonNull final ISubscriber subscriber, @NonNull final Topic topic) {
        //ISubscriber is a user facing subscriber
        //We create TopicSubscriber by composing ISubscriber into TopicSubscriber
        //Simply put, TopicSubscriber is a wrapper that stores additional details for subscriber without exposing those to the user
        //In this example, TopicSubscriber stores the offset to know from which offset the messages are to be read
        topic.addSubscriber(new TopicSubscriber(subscriber));
        System.out.println(subscriber.getId() + " is subscribed to " + topic.getTopicName());
    }

    @Override
    public void publish(@NonNull final Topic topic, @NonNull final Message message) {
        topic.addMessage(message);
        new Thread(() -> topicHandlers.get(topic.getTopicId()).publish()).start();
    }

    @Override
    public void resetOffset(@NonNull final Topic topic,  @NonNull final ISubscriber subscriber, final int offset) {
        for (TopicSubscriber topicSubscriber: topic.getSubscribers()) {
            if (topicSubscriber.getSubscriber().equals(subscriber)) {
                topicSubscriber.getOffset().set(offset);
                new Thread(() -> topicHandlers.get(topic.getTopicId()).startWorkerForSubscriber(topicSubscriber)).start();
                break;
            }
        }
    }
}
