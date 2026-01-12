package org.example.GraphExecutor.JobExecutor;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JobWorker implements Runnable {
    private Job job;
    private final JobRepository repository;

    @SneakyThrows
    @Override
    public void run() {
        synchronized (repository) {
            while (!canRun()) {
                repository.wait();
            }
        }

        job.doWork();

        synchronized (repository) {
            job.setState(JobState.COMPLETED);
            repository.notifyAll();
        }
    }

    private boolean canRun() {
        List<String> dependencies = job.getDependencies();
        List<Job> dependentJobs = dependencies.stream().map(repository::getJob).toList();

        for (Job jb : dependentJobs) {
            if (jb.getState() != JobState.COMPLETED) {
                return false;
            }
        }
        return true;
    }
}
