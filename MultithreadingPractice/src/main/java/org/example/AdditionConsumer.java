package org.example;

import lombok.SneakyThrows;

public class AdditionConsumer implements IConsumer {
    @Override
    @SneakyThrows
    public void consume(IMessage message) {
        Message addMessage = (Message) message;
        System.out.println("[" + Thread.currentThread().getName() + "]" + "AdditionConsumer : Received message with args = " + message.toString());
        System.out.println("[" + Thread.currentThread().getName() + "]" + "AdditionConsumer : Processing message...");
        Thread.sleep(1000 * 10);
        System.out.println("[" + Thread.currentThread().getName() + "]" + "AdditionConsumer : Processed with result = " + (addMessage.getA() + addMessage.getB()));
    }
}
