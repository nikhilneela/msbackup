package org.example.BathroomManagement;

import lombok.AllArgsConstructor;

public class Bathroom {
    public PoliticalParty currentParty;
    public int currentOccupants;
    public int maxAllowedOccupants;

    public Bathroom(int maxAllowedOccupants) {
        this.maxAllowedOccupants = maxAllowedOccupants;
    }
}
