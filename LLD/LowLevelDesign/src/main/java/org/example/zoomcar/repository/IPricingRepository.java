package org.example.zoomcar.repository;

import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.VehicleType;

public interface IPricingRepository {
    void addPrice(Branch branch, VehicleType type, double price);
    double getPrice(Branch branch, VehicleType type);
}
