package org.example.zoomcar.repository;

import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.Vehicle;
import org.example.zoomcar.model.VehicleType;

import java.util.List;

public interface IBranchRepository {
    Branch addBranch(String name);
    Branch getBranch(String name);
    List<Branch> getAllBranches();
    List<Vehicle> getAllVehicles(Branch branch, VehicleType type);
    void addVehicle(Branch branch, Vehicle vehicle);
    List<Vehicle> getAllVehicles(Branch branch);
}
