package org.example.taskscheduler.executors;

import org.example.taskscheduler.task.ITask;

public interface ITaskExecutor {
        void execute(ITask task) throws Exception;
        boolean doesSupport(ITask task);
    }
