package org.example.ratelimiter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class Node {
    private String key;
    @Getter
    private long timeInMills;
}
