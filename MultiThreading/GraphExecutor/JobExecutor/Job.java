package org.example.GraphExecutor.JobExecutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Job {
    private String id;
    private List<String> dependencies;
    @Setter
    private JobState state;
    public void doWork() throws InterruptedException {
        System.out.println("Started running job with id = " + id);
        Thread.sleep(5000);
        System.out.println("Finished running job with id = " + id);
    }
}
