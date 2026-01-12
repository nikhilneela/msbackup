package model;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Topic {
    private final String topicName;
    private final String topicId;
    private final List<Message> messages;
    private final List<TopicSubscriber> subscribers;

    public Topic(String topicName, String topicId) {
        this.topicName = topicName;
        this.topicId = topicId;
        this.messages = new ArrayList<>();
        this.subscribers = new ArrayList<>();
    }

    public void addMessage(@NonNull final Message message) {
        messages.add(message);
    }

    public void addSubscriber(@NonNull final TopicSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public List<TopicSubscriber> getSubscribers() {
        return this.subscribers;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public String getTopicId() {
        return this.topicId;
    }

    public String getTopicName() {
        return this.topicName;
    }
}
