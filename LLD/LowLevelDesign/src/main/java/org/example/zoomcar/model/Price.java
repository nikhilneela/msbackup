package org.example.zoomcar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Price {
    private String id;
    private String branchId;
    private VehicleType vehicleType;
    private double price;
}
