package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import public_interface.ISubscriber;
import java.util.concurrent.atomic.AtomicInteger;
@Getter
@AllArgsConstructor
public class TopicSubscriber {
    private final AtomicInteger offset;
    private final ISubscriber subscriber;

    public TopicSubscriber(@NonNull final ISubscriber subscriber) {
        this.offset = new AtomicInteger(0);
        this.subscriber = subscriber;
    }

    public ISubscriber getSubscriber(){
        return subscriber;
    }

    public AtomicInteger getOffset() {
        return offset;
    }
}
