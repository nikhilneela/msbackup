package org.example.GraphExecutor.JobExecutor;

import java.util.ArrayList;
import java.util.List;

public class GraphRunner {
    public static void main(String[] args) {
        //create jobs
        Job jobA = new Job("A", List.of(), JobState.NOT_STARTED);
        Job jobB = new Job("B", List.of(jobA.getId()), JobState.NOT_STARTED);
        Job jobC = new Job("C", List.of(jobB.getId(), jobA.getId()), JobState.NOT_STARTED);
        Job jobE = new Job("E", List.of(jobA.getId()), JobState.NOT_STARTED);
        Job jobF = new Job("F", List.of(jobA.getId(), jobE.getId()), JobState.NOT_STARTED);
        Job jobK = new Job("K", List.of(), JobState.NOT_STARTED);
        Job jobL = new Job("L", List.of(jobK.getId()), JobState.NOT_STARTED);
        Job jobM = new Job("M", List.of(jobK.getId(), jobL.getId()), JobState.NOT_STARTED);
        Job jobG = new Job("G", List.of(jobA.getId(), jobB.getId(), jobC.getId(), jobE.getId(), jobF.getId(), jobM.getId()), JobState.NOT_STARTED);


        List<Job> jobs = new ArrayList<>();
        jobs.add(jobA);
        jobs.add(jobB);
        jobs.add(jobC);
        jobs.add(jobE);
        jobs.add(jobF);
        jobs.add(jobG);
        jobs.add(jobK);
        jobs.add(jobL);
        jobs.add(jobM);

        Pipeline pipeline = new SinglePipeline(new JobRepository(jobs));
        pipeline.execute();
    }
}
