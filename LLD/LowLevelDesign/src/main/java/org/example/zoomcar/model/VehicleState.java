package org.example.zoomcar.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VehicleState {
    private final String regNumber;
    private final boolean isAvailable;

    @Override
    public String toString() {
        return "VehicleState{" +
                "regNumber='" + regNumber + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
