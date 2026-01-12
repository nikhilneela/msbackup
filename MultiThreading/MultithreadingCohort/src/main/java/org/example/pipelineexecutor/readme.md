Design and implement a Pipeline Manager capable of handling job execution with dependencies.

Requirements:
Job Structure:
Each job is represented by:

Job {  
jobId  
doWork() throws JobException
}  
jobId uniquely identifies the job.
doWork() executes the job and throws an JobException in case of a failure.
Dependency Management:

Jobs can have dependencies on one or more preceding jobs.
Example graph:
A --> B --> C ------
|                  |  
|                   --> G  
---> E --> F--------^  
K --> L --> M  
Execution Requirements:

Implement an execute() function in the Pipeline Manager class to process jobs in dependency order.
A job should only start after all its dependent jobs have successfully completed.
Pipeline Termination Conditions:

Success: When all jobs have executed successfully.
Failure: If at least one job fails, the pipeline terminates immediately, with no job left in a running state.
Expectations:
Dependency resolution.
Error handling to ensure proper termination on failure.
Optimal job scheduling to minimize idle time where possible.
Run jobs in parallel using multiple threads whenever possible.