package org.example.h2o;

class WaterMoleculeWorker implements Runnable {
    private State state;

    public WaterMoleculeWorker(final State state) {
        this.state = state;
    }

    public void run() {
        while (true) {
            synchronized(state) {
                if (!state.isWaterPossible()) {
                    try {
                        state.wait();
                    }
                    catch (Exception ex) {

                    }
                }
                state.releaseWaterMolecule();
            }
        }
    }
}