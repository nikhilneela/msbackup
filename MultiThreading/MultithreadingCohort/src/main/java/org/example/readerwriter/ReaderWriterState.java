package org.example.readerwriter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaderWriterState {
    private WorkerType current;
    private Integer count;

    public ReaderWriterState() {
        this.current = null;
        this.count = 0;
    }
}
