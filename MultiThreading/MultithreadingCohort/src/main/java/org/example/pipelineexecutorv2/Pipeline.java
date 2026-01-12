package org.example.pipelineexecutorv2;

import lombok.AllArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
public class Pipeline {
    private final HashMap<Job, List<Job>> graph;
    private final Queue<Job> executionQueue;
    private final Queue<Job> completedQueue;
    private final SchedulerWorker schedulerWorker;
    private final ArrayList<Job> jobs;
    private final int numExecutors;
    private final AtomicBoolean shutdown;
    private final AtomicBoolean failed;

    public Pipeline(final HashMap<Job, List<Job>> graph, final ArrayList<Job> jobs, int numExecutors) {
        this.graph = graph;
        this.executionQueue = new LinkedList<>();
        this.completedQueue = new LinkedList<>();
        this.jobs = jobs;
        this.shutdown = new AtomicBoolean(false);
        this.failed = new AtomicBoolean(false);
        this.schedulerWorker = new SchedulerWorker(this.graph, executionQueue, this.completedQueue, this.jobs, shutdown, failed);
        this.numExecutors = numExecutors;
    }

    public void start() {
        new Thread(this.schedulerWorker, "Scheduler").start();
        for (int i = 0; i < numExecutors; ++i) {
            new Thread(new ExecutorWorker(executionQueue, completedQueue, shutdown, failed, this), "Executor{" + i + "}").start();
        }
    }

    public void requestShutdownDueToFailure() {
        //someone has already set failed, do not set again, just return
        if (!failed.compareAndSet(false, true)) {
            return;
        }

        //mark pipeline as shutting down
        shutdown.set(true);

        //signal scheduler to exit

        synchronized (completedQueue) {
            completedQueue.notify();
        }

        //signal other executors to exit
        synchronized (executionQueue) {
            executionQueue.notifyAll();
        }
    }
}
