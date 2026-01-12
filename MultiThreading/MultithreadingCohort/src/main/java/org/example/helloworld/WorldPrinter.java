package org.example.helloworld;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class WorldPrinter implements Runnable {
    private final SyncState state;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i <= 100; ++i) {
            synchronized (state) {
                while (!state.canPrintWorld()) {
                    state.wait();
                }
                System.out.println("World");
                if (i % 10 == 0) {
                    state.setCanWorldPrint(false);
                    state.setCanHelloPrint(true);
                    state.notifyAll();
                }
            }
        }
    }
}
