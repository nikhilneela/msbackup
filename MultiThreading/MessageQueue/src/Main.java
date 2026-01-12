import model.Message;
import model.Topic;
import public_interface.IMessageQueue;
import public_interface.TopicQueue;

public class Main {
    public static void main(String[] args) {

        SleepingSubscriber subscriber1 = new SleepingSubscriber("sub1", 10000);
        SleepingSubscriber subscriber2 = new SleepingSubscriber("sub2", 15000);

        IMessageQueue messageQueue = new TopicQueue();

        Topic t1 = messageQueue.createTopic("t1");
        Topic t2 = messageQueue.createTopic("t2");

        messageQueue.subscribe(subscriber1, t1);
        messageQueue.subscribe(subscriber2, t2);


        messageQueue.publish(t1, new Message("m1"));
        messageQueue.publish(t1, new Message("m2"));

        messageQueue.publish(t2, new Message("m3"));
    }
}