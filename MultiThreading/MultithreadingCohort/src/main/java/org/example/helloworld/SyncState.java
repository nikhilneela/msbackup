package org.example.helloworld;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor

public class SyncState {
    private boolean canHelloPrint;
    private boolean canWorldPrint;

    public boolean canPrintHello() {
        return canHelloPrint;
    }

    public boolean canPrintWorld() {
        return canWorldPrint;
    }

    public void setCanHelloPrint(boolean value) {
        this.canHelloPrint = value;
    }

    public void setCanWorldPrint(boolean value) {
        this.canWorldPrint = value;
    }
}
