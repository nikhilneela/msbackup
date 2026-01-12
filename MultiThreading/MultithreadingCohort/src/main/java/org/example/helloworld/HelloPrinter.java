package org.example.helloworld;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class HelloPrinter implements Runnable {
    private final SyncState state;

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i <= 100; ++i) {
            synchronized (state) {
                while (!state.canPrintHello()) {
                    state.wait();
                }
                System.out.println("Hello");
                if (i % 10 == 0) {
                    state.setCanHelloPrint(false);
                    state.setCanWorldPrint(true);
                    state.notifyAll();
                }
            }
        }
    }
}
