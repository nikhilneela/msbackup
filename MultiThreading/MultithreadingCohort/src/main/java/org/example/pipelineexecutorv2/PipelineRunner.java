package org.example.pipelineexecutorv2;


import java.util.*;

public class PipelineRunner {

    private static ArrayList<Job> createJobs() {
        ArrayList<Job> jobs = new ArrayList<>();
        jobs.add(new Job("A", 5000));
        jobs.add(new Job("B", 8000));
        jobs.add(new Job("C", 3000));
        jobs.add(new Job("E", 5000));
        jobs.add(new Job("F", 9000));
        jobs.add(new Job("G", 1000));
        jobs.add(new Job("K", 3000));
        jobs.add(new Job("L", 8000));
        jobs.add(new Job("M", 1000));
        return jobs;
    }

    private static HashMap<Job, List<Job>> createGraph(ArrayList<Job> jobs) {
        HashMap<Job, List<Job>> map = new HashMap<>();
        map.put(getJob(jobs, "A"), Arrays.asList(getJob(jobs, "B"), getJob(jobs, "E")));
        map.put(getJob(jobs, "B"), Collections.singletonList(getJob(jobs, "C")));
        map.put(getJob(jobs, "E"), Collections.singletonList(getJob(jobs, "F")));
        map.put(getJob(jobs, "C"), Collections.singletonList(getJob(jobs, "G")));
        map.put(getJob(jobs, "F"), Collections.singletonList(getJob(jobs, "G")));
        map.put(getJob(jobs, "K"), Collections.singletonList(getJob(jobs, "L")));
        map.put(getJob(jobs, "L"), Collections.singletonList(getJob(jobs, "M")));
        return map;
    }

    private static Job getJob(final ArrayList<Job> jobs, final String id) {
        return jobs.stream().filter(job -> job.getId().equals(id)).findFirst().orElseThrow();
    }

    public static void main(String[] args) {

        ArrayList<Job> jobs = createJobs();
        HashMap<Job, List<Job>> graph = createGraph(jobs);

        Pipeline pipeline = new Pipeline(graph, jobs, 3);
        pipeline.start();
    }
}
