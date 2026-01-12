package org.example.kafka;

import lombok.SneakyThrows;
import org.example.sqsqueue.AdditionConsumer;
import org.example.sqsqueue.Message;
import org.example.sqsqueue.SubstractionConsumer;

import java.util.List;

public class KafkaRunner {
    @SneakyThrows
    public void run() {
        Kafka kafka = Kafka.getInstance();
        String addSubscription = "add";
        String subSubscription = "sub";
        ISubscriber subscriber1 = new AdditionSubscriber(List.of(new AdditionConsumer(), new AdditionConsumer(), new AdditionConsumer()));
        ISubscriber subscriber2 = new SubstractionSubscriber(List.of(new SubstractionConsumer()));
        kafka.registerSubscriber(addSubscription, subscriber1);
        kafka.registerSubscriber(subSubscription, subscriber2);

        kafka.publish(new Message(2, 3));
        kafka.publish(new Message(4, 5));
        kafka.publish(new Message(6, 7));
        kafka.publish(new Message(8, 9));

        Thread.sleep(10*1000);

        kafka.addConsumer(addSubscription, new AdditionConsumer());

        kafka.publish(new Message(7, 21));
        kafka.publish(new Message(7, 21));
        kafka.publish(new Message(7, 21));
        kafka.publish(new Message(7, 21));

    }
}
