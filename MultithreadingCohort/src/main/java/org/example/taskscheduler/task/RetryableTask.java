package org.example.taskscheduler.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@AllArgsConstructor
@Getter
public class RetryableTask implements ITask {
    private final ITask wrappedTask;
    private final int maxRetries;
    private final long retryIntervalMillis;

    @Override
    public Long getExecutionTime() {
        return wrappedTask.getExecutionTime();
    }

    @Override
    @SneakyThrows
    public void run() {
        int attempts = 0;
        while (true) {
            try {
                wrappedTask.run();
            } catch (Exception ex) {
                attempts++;
                if (attempts > maxRetries) {
                    throw ex;
                }
                Thread.sleep(retryIntervalMillis);
            }
        }
    }
}
