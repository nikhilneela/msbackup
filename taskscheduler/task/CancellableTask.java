package org.example.taskscheduler.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.taskscheduler.helper.Logger;

import java.util.Date;

public class CancellableTask implements ITask {
    private final ITask wrappedTask;
    @Getter
    private boolean isCancelled;

    public CancellableTask(final ITask task) {
        this.wrappedTask = task;
    }

    @Override
    public Long getExecutionTime() {
        return wrappedTask.getExecutionTime();
    }

    @Override
    public void run() {
        if (!isCancelled) {
            wrappedTask.run();
        }
    }

    public void cancel() {
        Logger.formatAndLog("Cancelled task with execution time : " + new Date(wrappedTask.getExecutionTime()));
        isCancelled = true;
    }
}
