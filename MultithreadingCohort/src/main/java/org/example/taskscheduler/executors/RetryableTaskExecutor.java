package org.example.taskscheduler.executors;

import org.example.taskscheduler.helper.Logger;
import org.example.taskscheduler.task.ITask;
import org.example.taskscheduler.task.RetryableTask;

public class RetryableTaskExecutor extends ExecutorChain {
    @Override
    public void execute(ITask task) {
        RetryableTask retryableTask = (RetryableTask) task;
        int numRetries = 0;

        while (numRetries < retryableTask.getMaxRetries()) {
            try {
                if (next != null) {
                    next.execute(task);
                }
                return;
            } catch (Exception e) {
                numRetries++;
                Logger.formatAndLog("retry attempt " + numRetries + " failed");
            }
        }
        Logger.formatAndLog("All retires exhausted..");
    }

    @Override
    public boolean doesSupport(ITask task) {
        return task instanceof RetryableTask;
    }
}
