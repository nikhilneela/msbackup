package org.example.pipelineexecutor;

import lombok.SneakyThrows;
import org.example.cache.StripedHashMap;
import org.example.cache.ThreadSafeHashMap;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Pipeline {
    private final HashMap<Job, List<Job>> graph;
    private final Queue<Job> executionQueue;
    private final Map<Job, Integer> inDegreeMap;
    private final ArrayList<Job> jobs;
    private int executedCount;
    private final Integer numThreads;


    public Pipeline(final HashMap<Job, List<Job>> graph, final ArrayList<Job> jobs, final Integer numThreads) {
        this.graph = graph;
        this.executionQueue = new LinkedList<>();
        this.inDegreeMap = new ConcurrentHashMap<>();
        this.jobs = jobs;
        this.numThreads = numThreads;
        Thread[] workers = new Thread[numThreads];

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
                while (executionQueue.isEmpty() && executedCount < jobs.size()) {
                    executionQueue.wait();
                }

                if (executedCount >= jobs.size()) {
                    return;
                }
                eligibleJob = executionQueue.poll();
                executedCount++;
            }

            try {
                eligibleJob.doWork();
            } catch (JobException jobException) {
                Logger.formatAndLog("Received exception while executing job(" + eligibleJob.getId() + "). Terminating pipeline");
                return;
            }

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
        }
    }
}
