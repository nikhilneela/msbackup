package org.example.GraphExecutor.JobExecutor;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class SinglePipeline implements Pipeline {
    private JobRepository repository;
    @Override
    public void execute() {
        for (Job job : repository.getAllJobs()) {
            new Thread(new JobWorker(job, repository)).start();
        }
    }
}
