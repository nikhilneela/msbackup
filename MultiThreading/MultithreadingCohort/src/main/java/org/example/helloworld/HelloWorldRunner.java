package org.example.helloworld;

public class HelloWorldRunner {
    public static void main(String[] args) {
        SyncState state = new SyncState(true, false);
        Thread helloThread = new Thread(new HelloPrinter(state));
        Thread worldThread = new Thread(new WorldPrinter(state));
        helloThread.start();
        worldThread.start();
    }
}
