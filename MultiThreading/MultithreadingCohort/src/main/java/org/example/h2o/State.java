package org.example.h2o;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class State {
    private int oxygen;
    private int hydrogen;

    public boolean isWaterPossible() {
        return this.hydrogen >= 2 && this.oxygen >= 1;
    }

    public void releaseWaterMolecule() {
        synchronized (this) {
            this.oxygen -= 1;
            this.hydrogen -= 2;
        }
    }

}
