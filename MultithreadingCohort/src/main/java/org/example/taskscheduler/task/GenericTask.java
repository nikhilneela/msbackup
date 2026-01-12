package org.example.taskscheduler.task;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GenericTask<T> implements ITask<T> {
    private final ITask<T> t;

    @Override
    public Long getExecutionTime() {
        //return executionTime;
    }

    @Override
    public T execute() {
        return null;
    }

}
