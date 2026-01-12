package org.example.threadsequencing;

public class Logger {
    public static void log(final String message) {
        System.out.println("[" + Thread.currentThread().getName() + "]" + message);
    }
}
