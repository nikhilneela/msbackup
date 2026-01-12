package org.example;

public class SQSRunner {
    public void run() {
        SQS sqs = SQS.getInstance();

        sqs.registerConsumer(new AdditionConsumer());
        sqs.registerConsumer(new AdditionConsumer());
        sqs.registerConsumer(new AdditionConsumer());

        sqs.send(new Message(4, 5));
        sqs.send(new Message(8, 13));
        sqs.send(new Message(7, 12));
        sqs.send(new Message(9, 23));
        sqs.send(new Message(6, 14));
        sqs.send(new Message(12, 31));
        sqs.send(new Message(12, 18));
        sqs.send(new Message(13, 19));
        sqs.send(new Message(14, 30));
        sqs.send(new Message(15, 21));

    }
}
