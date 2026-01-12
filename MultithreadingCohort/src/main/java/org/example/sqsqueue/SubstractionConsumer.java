package org.example.sqsqueue;

import lombok.SneakyThrows;

public class SubstractionConsumer extends BaseConsumer {
    @SneakyThrows
    @Override
    public void consume(IMessage message) {
        Message calculationMessage = (Message) message;
        formatAndLogMessage("SubstractionConsumer : Consumer received message : a = " + calculationMessage.getA() + " b = " + calculationMessage.getB());
        formatAndLogMessage("SubstractionConsumer : Consumer processing message...");
        Thread.sleep(5*1000);
        formatAndLogMessage("SubstractionConsumer : Consumer processed message with result = " + (calculationMessage.getA() - calculationMessage.getB()));
    }
}
