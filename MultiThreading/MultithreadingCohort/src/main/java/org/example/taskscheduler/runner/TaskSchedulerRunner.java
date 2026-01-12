package org.example.taskscheduler.runner;

import org.example.taskscheduler.executors.SimpleTaskExecutor;
import org.example.taskscheduler.helper.Logger;
import org.example.taskscheduler.scheduler.TaskScheduler;
import org.example.taskscheduler.task.*;

import java.util.List;
import java.util.Scanner;

public class TaskSchedulerRunner {
    static TaskScheduler scheduler = new TaskScheduler(25, null);
    public static boolean tryParse(String input, int[] result) {
        try {
            result[0] = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    public static void interactiveTaskSubmitter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Scheduler has started. Please submit a task by entering the time after which it has to run. Enter q to exit");
        while (true) {
            System.out.print("Enter your choice : ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            int[] delay = new int[1];
            if (tryParse(line, delay)) {
                Long futureTime = delay[0] * 1000L + System.currentTimeMillis();
                ITask task = new ITask() {
                    @Override
                    public Long getExecutionTime() {
                        return futureTime;
                    }

                    @Override
                    public void execute() throws Exception {
                        Thread.sleep(10*1000);
                    }
                };
                scheduler.submitTask(task);
            } else {
                break;
            }
        }
    }
}
