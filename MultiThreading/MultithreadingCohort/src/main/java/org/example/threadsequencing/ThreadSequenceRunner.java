package org.example.threadsequencing;

import java.util.concurrent.ConcurrentHashMap;

public class ThreadSequenceRunner {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
        Thread[] messageThreads = new Thread[Messages.values().length];
        SyncState state = new SyncState(map);
        int length = Messages.values().length;
        for (int i = 0; i < length; ++i) {
            String message = Messages.values()[i].name();
            String nextMessage = Messages.values()[(i + 1) % length].name();

            if (i == 0) {
                map.put(message, true);
            } else {
                map.put(message, false);
            }
            messageThreads[i] = new Thread(new MessagePrinter(state, message, nextMessage, 1));
        }

        for (Thread messageThread : messageThreads) {
            messageThread.start();
        }
    }
}
