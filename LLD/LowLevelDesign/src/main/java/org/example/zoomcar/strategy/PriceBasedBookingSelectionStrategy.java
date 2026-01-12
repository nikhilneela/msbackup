package org.example.zoomcar.strategy;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.Vehicle;
import org.example.zoomcar.service.BranchService;

import java.util.List;

@AllArgsConstructor
public class PriceBasedBookingSelectionStrategy implements IBookingSelectionStrategy {
    private final BranchService branchService;

    @Override
    public Vehicle getSelectedVehicle(final List<Vehicle> vehicles) {
        List<Vehicle> sorted = vehicles.stream().sorted((a, b) -> {
            Branch aBranch = branchService.getBranchById(a.getBranchId());
            Branch bBranch = branchService.getBranchById(b.getBranchId());
            return (int) (branchService.getPrice(aBranch.getName(), a.getVehicleType()) - branchService.getPrice(bBranch.getName(), b.getVehicleType()));
        }).toList();
        return sorted.getFirst();
    }
}
