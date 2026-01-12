package org.example.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.sqsqueue.IMessage;
@Getter
@AllArgsConstructor
public class OrderMessage implements IMessage {
    private final String orderId;
}
