package org.example.sqsqueue;

import lombok.SneakyThrows;

public class AdditionConsumer extends BaseConsumer {
    @SneakyThrows
    @Override
    public void consume(IMessage message) {
        Message calculationMessage = (Message) message;
        formatAndLogMessage("AdditionConsumer : Consumer received message : a = " + calculationMessage.getA() + " b = " + calculationMessage.getB());
        formatAndLogMessage("AdditionConsumer : Consumer processing message...");
        Thread.sleep(5*1000);
        formatAndLogMessage("AdditionConsumer : Consumer processed message with result = " + (calculationMessage.getA() + calculationMessage.getB()));
    }
}
