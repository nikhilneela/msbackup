package org.example.taskscheduler.task;

import java.util.concurrent.CompletableFuture;

public interface ITask<T>  {
    Long getExecutionTime();
    CompletableFuture<T> execute() throws Exception;
}
