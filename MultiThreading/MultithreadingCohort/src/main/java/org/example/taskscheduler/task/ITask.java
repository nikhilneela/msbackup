package org.example.taskscheduler.task;

import java.util.concurrent.CompletableFuture;

public interface ITask {
    Long getExecutionTime();
    void execute() throws Exception;
}
