package org.example.taskscheduler.executors;

import org.example.taskscheduler.task.ITask;

public interface ITaskExecutor {
        void execute(ITask task);
        boolean doesSupport(ITask task);
    }
