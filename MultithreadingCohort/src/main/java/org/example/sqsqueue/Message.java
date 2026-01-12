package org.example.sqsqueue;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Message implements IMessage {
    private Integer a;
    private Integer b;
}
