package org.example.readerwriter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Worker {
    private final String id;
    private final WorkerType type;
    private final Long accessTime;
}
