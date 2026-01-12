package org.example.BathroomManagement;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

@AllArgsConstructor
public class BathroomManager {
    private final Bathroom bathroom;

    @SneakyThrows
    public void democrat(final String name) {
        System.out.println("Democrat : " + name + " is waiting for bathroom");
        synchronized (bathroom) {
            while (bathroom.currentParty == PoliticalParty.R || bathroom.currentOccupants == bathroom.maxAllowedOccupants) {
                bathroom.wait();
            }
            bathroom.currentParty = PoliticalParty.D;
            bathroom.currentOccupants += 1;
            System.out.println("Democrat : " + name + " is using the bathroom");
        }

        Thread.sleep(5 * 1000);

        synchronized (bathroom) {
            System.out.println("Democrat : " + name + " finished using the bathroom");
            bathroom.currentOccupants -= 1;
            if (bathroom.currentOccupants == 0) {
                bathroom.currentParty = null;
            }
            bathroom.notifyAll();
        }
    }

    @SneakyThrows
    public void republican(final String name) {
        System.out.println("Republican : " + name + " is waiting for bathroom");
        synchronized (bathroom) {
            while (bathroom.currentParty == PoliticalParty.D || bathroom.currentOccupants == bathroom.maxAllowedOccupants) {
                bathroom.wait();
            }
            bathroom.currentParty = PoliticalParty.R;
            bathroom.currentOccupants += 1;
            System.out.println("Republican : " + name + " is using the bathroom");
        }

        Thread.sleep(5 * 1000);

        synchronized (bathroom) {
            System.out.println("Republican : " + name + " finished using the bathroom");
            bathroom.currentOccupants -= 1;
            if (bathroom.currentOccupants == 0) {
                bathroom.currentParty = null;
            }
            bathroom.notifyAll();
        }
    }
}
