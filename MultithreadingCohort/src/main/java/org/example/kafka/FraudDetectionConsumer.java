package org.example.kafka;

import lombok.SneakyThrows;
import org.example.sqsqueue.BaseConsumer;
import org.example.sqsqueue.IMessage;

public class FraudDetectionConsumer extends BaseConsumer {
    @Override
    @SneakyThrows
    public void consume(IMessage message) {
        OrderMessage orderMessage = (OrderMessage) message;
        formatAndLogMessage("FraudDetectionConsumer : Received message with orderId = " + orderMessage.getOrderId());
        formatAndLogMessage("FraudDetectionConsumer : Processing message");
        Thread.sleep(10*1000);
        formatAndLogMessage("FraudDetectionConsumer : Processed message with orderId = " + orderMessage.getOrderId());
    }
}
