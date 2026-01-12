package org.example.taskscheduler.executors;

import org.example.taskscheduler.helper.Logger;
import org.example.taskscheduler.task.ITask;

import java.util.Date;

public class SimpleTaskExecutor implements ITaskExecutor {
    @Override
    public void execute(ITask task) throws Exception {
        Logger.formatAndLog("Now executing task whose execution time is " + new Date(task.getExecutionTime()));
        task.execute();
        Logger.formatAndLog("Finished executing task");
    }

    @Override
    public boolean doesSupport(ITask task) {
        return true;
    }
}
