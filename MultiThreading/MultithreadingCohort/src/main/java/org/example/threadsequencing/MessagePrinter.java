package org.example.threadsequencing;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class MessagePrinter implements Runnable {
    private final SyncState state;
    private final String message;
    private final String nextMessage;
    private final int maxCount;

    private int count;

    @SneakyThrows
    @Override
    public void run() {
        while (count < maxCount) {
            synchronized (state) {
                while (!state.canPrint(message)) {
                    state.wait();
                }
                Logger.log(message);
                state.setPrint(message, false);
                state.setPrint(nextMessage, true);
                state.notifyAll();
            }
            count++;
        }
    }
}
