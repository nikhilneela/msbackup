package org.example.taskscheduler.helper;

import java.util.Date;

public class Logger {
    public static void formatAndLog(final String message) {
        System.out.println("[" + Thread.currentThread().getName() + "]" + " [" + new Date(System.currentTimeMillis()) + "] " + message);
    }
}
