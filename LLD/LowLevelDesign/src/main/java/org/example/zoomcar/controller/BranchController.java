package org.example.zoomcar.controller;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.InventoryInfo;
import org.example.zoomcar.model.TimeSlot;
import org.example.zoomcar.model.VehicleType;
import org.example.zoomcar.service.BranchService;

import java.util.List;

@AllArgsConstructor
public class BranchController {
    private final BranchService branchService;

    public Branch addBranch(final String branchName) {
        Branch branch = branchService.getBranch(branchName);
        if (branch != null) {
            throw new RuntimeException("Branch with name " + branchName + " already exists");
        }
        return branchService.addBranch(branchName);
    }

    public void allocatePrice(final String branchName, final VehicleType type, double price) {
        branchService.addPrice(branchService.getBranch(branchName), type, price);
    }

    public void addVehicle(final String vehicleId, VehicleType type, final String branchName) {
        branchService.addVehicle(branchName, type, vehicleId);
    }

    public List<InventoryInfo> getInventoryInfo(final TimeSlot slot) {
        return branchService.getInventoryInfo(slot);
    }
}
