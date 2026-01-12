package org.example.GraphExecutor.JobExecutor;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class JobRepository {
    private List<Job> jobs;

    public Job getJob(String id) {
        return jobs.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow();
    }

    public List<Job> getAllJobs() {
        return jobs;
    }
}
