package org.example.pipelineexecutorv2;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.pipelineexecutor.JobException;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

@AllArgsConstructor
public class ExecutorWorker implements Runnable {
    private final Queue<Job> executionQueue;
    private final Queue<Job> completedQueue;
    private final AtomicBoolean shutdown;
    private final AtomicBoolean failed;
    private final Pipeline pipeline;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Job jobToRun;
            synchronized (executionQueue) {
                while (executionQueue.isEmpty() && !shutdown.get()) {
                    executionQueue.wait();
                }
                if (shutdown.get()) {
                    Logger.formatAndLog("Executor shutting down");
                    break;
                }
                jobToRun = executionQueue.poll();
            }

            if (jobToRun == null) {
                continue;
            }

            try {
                jobToRun.doWork();
            } catch (JobException jobException) {
                Logger.formatAndLog("Received exception when running Job(" + jobToRun.getId() + ")");
                pipeline.requestShutdownDueToFailure();
                break;
            }

            synchronized (completedQueue) {
                completedQueue.add(jobToRun);
                completedQueue.notifyAll();
            }
        }
    }
}
