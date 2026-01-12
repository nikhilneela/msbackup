package org.example.taskscheduler.scheduler;

import lombok.SneakyThrows;
import org.example.taskscheduler.helper.Logger;
import org.example.taskscheduler.task.ITask;
import org.example.taskscheduler.executors.ITaskExecutor;
import org.example.taskscheduler.executors.SimpleTaskExecutor;

import java.util.*;

public class TaskScheduler {
    private final PriorityQueue<ITask> scheduledTasks;
    private final Queue<ITask> readyTasks;
    private final Queue<ITask> retryTasks;

    private static TaskScheduler _instance;
    private List<ITaskExecutor> executors;
    private SimpleTaskExecutor defaultTaskExecutor;

    public TaskScheduler(int poolSize, final List<ITaskExecutor> executors) {
        this.scheduledTasks = new PriorityQueue<>(Comparator.comparing(ITask::getExecutionTime));
        this.readyTasks = new LinkedList<>();
        this.retryTasks = new LinkedList<>();
        Thread[] workerThreads = new Thread[poolSize];

        new Thread(this::runScheduler, "scheduler-thread").start();

        for (int i = 0; i < poolSize; ++i) {
            workerThreads[i] = new Thread(this::runWorker, "worker-thread-" + i);
            workerThreads[i].start();
        }
        this.executors = executors;
        this.defaultTaskExecutor = new SimpleTaskExecutor();
    }

    @SneakyThrows
    private void runScheduler() {
        while (true) {
            synchronized (scheduledTasks) {
                while (scheduledTasks.isEmpty()) {
                    scheduledTasks.wait();
                }
                while(!scheduledTasks.isEmpty()) {
                    ITask task = scheduledTasks.peek();
                    if (System.currentTimeMillis() >= task.getExecutionTime()) {
                        task = scheduledTasks.poll();
                        synchronized (readyTasks) {
                            this.readyTasks.add(task);
                            readyTasks.notify(); //signal workers to pickup from readyQueue
                        }
                    } else {
                        long waitTime = task.getExecutionTime() - System.currentTimeMillis();
                        scheduledTasks.wait(waitTime);
                        break;
                    }
                }
            }
        }
    }

    @SneakyThrows
    private void runWorker() {
        while(true) {
            ITask taskToRun;
            synchronized (readyTasks) {
                while (readyTasks.isEmpty()) {
                    readyTasks.wait();
                }
                taskToRun = readyTasks.poll();
            }
            try {
                ITaskExecutor executor = defaultTaskExecutor;
                if (executors != null) {
                    executor = executors.stream().filter(e -> e.doesSupport(taskToRun)).findFirst().orElse(defaultTaskExecutor);
                }
                executor.execute(taskToRun);
            } catch (Exception ex) {
                //catching is important as it would keep the thread alive
                System.out.println("Caught exception : " + ex);
                synchronized (retryTasks) {
                    retryTasks.add(taskToRun);
                    retryTasks.notifyAll();
                }
            }
        }
    }

    public void submitTask(ITask task) {
        synchronized (scheduledTasks) {
            Logger.formatAndLog("Submitted task with execution time : " + new Date(task.getExecutionTime()));
            scheduledTasks.add(task);
            scheduledTasks.notify();
        }
    }
}
