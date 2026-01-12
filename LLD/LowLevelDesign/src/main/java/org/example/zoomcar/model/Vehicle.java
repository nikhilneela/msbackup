package org.example.zoomcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Vehicle {
    private String id;
    private String regNumber;
    private VehicleType vehicleType;
    private String branchId;
}
