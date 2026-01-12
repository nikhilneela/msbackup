package org.example.sqsqueue;

import lombok.SneakyThrows;

public class SlowAdditionConsumer extends BaseConsumer {
    @Override
    @SneakyThrows
    public void consume(IMessage message) {
        Message calculationMessage = (Message) message;
        formatAndLogMessage("SlowAdditionConsumer : Received message with a = " + calculationMessage.getA() + " b = " + calculationMessage.getB());
        formatAndLogMessage("SlowAdditionConsumer : Processing message....");
        Thread.sleep(15*1000);
        formatAndLogMessage("SlowAdditionConsumer : Processed message with result = " + (calculationMessage.getA() + calculationMessage.getB()));
    }
}
