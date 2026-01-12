package org.example.sqsqueue;

public class SQSQueueRunner {
    public void run() {
        SQSQueue sqsQueue = new SQSQueue();
        sqsQueue.registerConsumer(new AdditionConsumer());
        sqsQueue.registerConsumer(new SlowAdditionConsumer());

        sqsQueue.publish(new Message(10,15));
        sqsQueue.publish(new Message(8, 12));
        sqsQueue.publish(new Message(13,19));
        sqsQueue.publish(new Message(21,23));
        sqsQueue.publish(new Message(33,18));
        sqsQueue.publish(new Message(44,26));
        sqsQueue.publish(new Message(29,34));
        sqsQueue.publish(new Message(37,51));
        sqsQueue.publish(new Message(41,29));
    }
}
