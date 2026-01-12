package org.example.pipelineexecutorv2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
public class SchedulerWorker implements Runnable {

    private final HashMap<Job, List<Job>> graph;
    private final Queue<Job> executionQueue;
    private final Queue<Job> completedQueue;
    private final ArrayList<Job> jobs;
    private final Map<Job, Integer> inDegreeMap;
    private final AtomicBoolean shutdown;
    private final AtomicBoolean failed;
    private int completedJobCount;

    public SchedulerWorker(final HashMap<Job, List<Job>> graph, final Queue<Job> executionQueue, final Queue<Job> completedQueue, final ArrayList<Job> jobs, AtomicBoolean shutdown, AtomicBoolean failed) {
        this.graph = graph;
        this.executionQueue = executionQueue;
        this.completedQueue = completedQueue;
        this.jobs = jobs;
        this.inDegreeMap = new ConcurrentHashMap<>();
        this.shutdown = shutdown;
        this.failed = failed;

        for (Map.Entry<Job, List<Job>> entries : graph.entrySet()) {
            List<Job> deps = entries.getValue();

            for (Job dep : deps) {
                inDegreeMap.put(dep, inDegreeMap.getOrDefault(dep, 0) + 1);
            }
        }
    }

    @SneakyThrows
    @Override
    public void run() {

        for (Job job : jobs) {
            if (!inDegreeMap.containsKey(job)) {
                synchronized (executionQueue) {
                    executionQueue.add(job);
                    executionQueue.notifyAll();
                }
            }
        }

        while (true) {
            Job completedJob;
            synchronized (completedQueue) {
                while (completedQueue.isEmpty() && !failed.get()) {
                    completedQueue.wait();
                }
                if (failed.get()) {
                    Logger.formatAndLog("One of the job failed. Scheduler terminating");
                    break;
                }
                completedJob = completedQueue.poll();
                completedJobCount++;
                if (completedJobCount == this.jobs.size()) {
                    this.shutdown.set(true);
                    synchronized (executionQueue) {
                        executionQueue.notifyAll();
                    }
                    Logger.formatAndLog("Finished executing all jobs");
                    break;
                }
            }
            List<Job> depJobs = graph.getOrDefault(completedJob, new ArrayList<>());

            for (Job dep : depJobs) {
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
