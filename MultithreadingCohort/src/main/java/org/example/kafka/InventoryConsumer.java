package org.example.kafka;

import lombok.SneakyThrows;
import org.example.sqsqueue.BaseConsumer;
import org.example.sqsqueue.IMessage;

public class InventoryConsumer extends BaseConsumer {
    @Override
    @SneakyThrows
    public void consume(IMessage message) {
        OrderMessage orderMessage = (OrderMessage) message;
        formatAndLogMessage("OrderConsumer : Received message with orderId = " + orderMessage.getOrderId());
        formatAndLogMessage("OrderConsumer : Processing message");
        Thread.sleep(10*1000);
        formatAndLogMessage("OrderConsumer : Processed message with orderId = " + orderMessage.getOrderId());
    }
}
