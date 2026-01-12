package org.example.readerwriter;

import lombok.SneakyThrows;

import java.util.LinkedList;
import java.util.Queue;

public class ReadersAndWritersService {
    private final ReaderWriterState state;

    public ReadersAndWritersService() {
        state = new ReaderWriterState();
    }

    @SneakyThrows
    public void access(final Worker worker) {
        Logger.formatAndLog("Worker with id = " + worker.getId() + " and type = " + worker.getType() + " is waiting to access Critical Section");
        synchronized (state) {
            while (!canEnter(worker)) {
                state.wait();
            }
            state.setCurrent(worker.getType());
            if (state.getCurrent() == WorkerType.READ) {
                state.setCount(state.getCount() + 1);
            }
         }
        criticalSection(worker);
        synchronized (state) {
            if (worker.getType() == WorkerType.WRITE) {
                state.setCurrent(null);
            } else {
                state.setCount(state.getCount() - 1);
                if (state.getCount() == 0) {
                    state.setCurrent(null);
                }
            }
            state.notifyAll();
        }
     }

     private boolean canEnter(final Worker worker) {
        if (this.state.getCurrent() == WorkerType.WRITE) {
            return false;
        }

        if (this.state.getCurrent() == WorkerType.READ && worker.getType() == WorkerType.READ) {
            return true;
        }

        return this.state.getCurrent() == null;
     }


    @SneakyThrows
    private void criticalSection(final Worker worker) {
        Logger.formatAndLog("Worker with id : " + worker.getId() + " and type = " + worker.getType() + " entered critical section");
        Thread.sleep(worker.getAccessTime());
        Logger.formatAndLog("Worker with id : " + worker.getId() + " and type = " + worker.getType() + " exited critical section");
    }
}
