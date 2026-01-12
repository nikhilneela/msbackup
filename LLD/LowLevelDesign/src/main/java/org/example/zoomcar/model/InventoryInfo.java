package org.example.zoomcar.model;

import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class InventoryInfo {
    private final String branchName;
    private final List<VehicleState> vehicleStates;

    @Override
    public String toString() {
        return "InventoryInfo{" +
                "branchName='" + branchName + '\'' +
                ", vehicleStates=" + vehicleStates +
                '}';
    }
}
