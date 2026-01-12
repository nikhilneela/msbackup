package public_interface;

import lombok.NonNull;
import model.Message;
import model.Topic;

public interface IMessageQueue {
    Topic createTopic(@NonNull final String topicName);
    void subscribe(@NonNull final ISubscriber subscriber, @NonNull final Topic topic);
    void publish(@NonNull final Topic topic, @NonNull final Message message);
    void resetOffset(@NonNull final Topic topic, @NonNull final ISubscriber subscriber, final int offset);
}