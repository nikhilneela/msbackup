package org.example.h2o;

class H2O {
    private final State state;

    public H2O() {
        this.state = new State();
        new Thread(new WaterMoleculeWorker(state)).start();
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (state) {
            state.setHydrogen(state.getHydrogen() + 1);
            state.notifyAll();
        }

        releaseHydrogen.run();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized(state) {
            state.setOxygen(state.getOxygen() + 1);
            state.notifyAll();
        }
        releaseOxygen.run();
    }
}
