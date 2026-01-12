package public_interface;

import model.Message;

public interface ISubscriber {
    String getId();
    void consume(Message message) throws InterruptedException;
}
