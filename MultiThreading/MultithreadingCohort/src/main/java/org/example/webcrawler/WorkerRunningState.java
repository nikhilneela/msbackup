package org.example.webcrawler;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkerRunningState {
    private final AtomicInteger counter;

    public WorkerRunningState(int numThreads) {
        this.counter = new AtomicInteger(numThreads);
    }

    public void setRunning() {
        this.counter.incrementAndGet();
    }

    public void setWaiting() {
        this.counter.decrementAndGet();
    }

    public boolean allWorkersWaiting() {
        return counter.get() == 0;
    }
}
