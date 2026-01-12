package org.example.pipelineexecutor;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class PipelineV2 {
    private final HashMap<Job, List<Job>> graph;
    private final Queue<Job> executionQueue;
    private final Map<Job, Integer> inDegreeMap;
    private final ArrayList<Job> jobs;
    private int executedCount;
    private AtomicBoolean failed;


    public PipelineV2(final HashMap<Job, List<Job>> graph, final ArrayList<Job> jobs) {
        this.graph = graph;
        this.executionQueue = new LinkedList<>();
        this.inDegreeMap = new ConcurrentHashMap<>();
        this.jobs = jobs;
        this.failed = new AtomicBoolean(false);
        Thread[] workers = new Thread[jobs.size()];

        for (int i = 0; i < workers.length; ++i) {
            workers[i] = new Thread(this::consume);
            workers[i].start();
        }
    }

    public void start() {
        for (Map.Entry<Job, List<Job>> entries : graph.entrySet()) {
            List<Job> deps = entries.getValue();

            for (Job dep : deps) {
                inDegreeMap.put(dep, inDegreeMap.getOrDefault(dep, 0) + 1);
            }
        }

        for (Job job : jobs) {
            if (!inDegreeMap.containsKey(job)) {
                synchronized (executionQueue) {
                    executionQueue.add(job);
                    executionQueue.notifyAll();
                }
            }
        }
    }

    @SneakyThrows
    private void consume() {
        while (true) {
            Job eligibleJob;
            synchronized (executionQueue) {
                while (executionQueue.isEmpty() && !failed.get()) {
                    executionQueue.wait();
                }

                if (failed.get() || executionQueue.isEmpty()) {
                    return;
                }
                eligibleJob = executionQueue.poll();
            }

            try {
                eligibleJob.doWork();
                List<Job> deps = graph.get(eligibleJob);
                if (deps != null) {
                    for (Job dep : deps) {
                        Integer newVal = inDegreeMap.compute(dep, (k, v) -> v == null ? 0 : v - 1);
                        if (newVal == 0) {
                            synchronized (executionQueue) {
                                executionQueue.add(dep);
                                executionQueue.notifyAll();
                            }
                        }
                    }
                }
            } catch (JobException jobException) {
                Logger.formatAndLog("Received exception while executing job(" + eligibleJob.getId() + "). Terminating pipeline");
                failed.set(true);
                return;
            }
        }
    }
}
