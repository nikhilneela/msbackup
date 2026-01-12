import model.Message;
import public_interface.ISubscriber;

public class SleepingSubscriber implements ISubscriber {
    private final String id;
    private final int sleepTimeInMillis;

    public SleepingSubscriber(String id, int sleepTimeInMillis) {
        this.id = id;
        this.sleepTimeInMillis = sleepTimeInMillis;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void consume(Message message) throws InterruptedException {
        System.out.println("Subscriber : " + id + " started consuming " + message.getMessage());
        Thread.sleep(sleepTimeInMillis);
        System.out.println("Subscriber : " + id + " finished consuming " + message.getMessage());
    }
}
