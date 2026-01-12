package org.example.taskscheduler.runner;

import org.example.taskscheduler.executors.RetryableTaskExecutor;
import org.example.taskscheduler.executors.SimpleTaskExecutor;
import org.example.taskscheduler.helper.Logger;
import org.example.taskscheduler.scheduler.TaskScheduler;
import org.example.taskscheduler.task.*;

import java.util.List;
import java.util.Scanner;

public class TaskSchedulerRunner {
    static TaskScheduler scheduler = new TaskScheduler(25, List.of(new SimpleTaskExecutor(), new RetryableTaskExecutor(), new CancellableTaskExecutor()));
    public static boolean tryParse(String input, int[] result) {
        try {
            result[0] = Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            return false;
        }
        return true;
    }

    private static void interactiveTaskSubmitter() {
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
                GenericTask simpleTask = new GenericTask(() -> {
                    try {
                        Thread.sleep(3*1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }, (delay[0] *1000L + System.currentTimeMillis()));
                scheduler.submitTask(simpleTask);
            } else {
                break;
            }
        }
    }

    public static void run() {
//        interactiveTaskSubmitter();

        GenericTask task = new GenericTask(() -> {
            Logger.formatAndLog("Attempting to execute task...");
            try {
                Thread.sleep(5*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            throw new RuntimeException("Simulated failure");
        }, System.currentTimeMillis() + 5*1000);
        CancellableTask cancellableTask = new CancellableTask(task);
        RetryableTask retryableTask = new RetryableTask(cancellableTask, 10);

        scheduler.submitTask(retryableTask);

        new Thread(() -> {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            cancellableTask.cancel();
        }).start();

    }
}
