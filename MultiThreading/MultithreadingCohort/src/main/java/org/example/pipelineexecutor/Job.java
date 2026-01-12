package org.example.pipelineexecutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Job {
    private final String id;
    private final long executionTimeInMills;

    @SneakyThrows
    public void doWork() throws JobException {
        Logger.formatAndLog("Job(" + id + ")" + " Started Execution");
        Thread.sleep(executionTimeInMills);
        Logger.formatAndLog("Job(" + id + ")" + " Finished Execution");
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(id, job.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
