package org.example.zoomcar.repository;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.Branch;
import org.example.zoomcar.model.Vehicle;
import org.example.zoomcar.model.VehicleType;

import java.util.*;

public class InMemoryBranchRepository implements IBranchRepository {
    private final List<Branch> branches;
    private final Map<String, List<Vehicle>> branchToVehicleMap;

    public InMemoryBranchRepository() {
        branches = new ArrayList<>();
        branchToVehicleMap = new HashMap<>();
        //load from a store
    }


    @Override
    public Branch addBranch(String name) {
        Branch branch = new Branch(UUID.randomUUID().toString(), name);
        branches.add(branch);
        return branch;
    }

    @Override
    public Branch getBranch(final String name) {
        return this.branches.stream().filter(branch -> branch.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    @Override
    public List<Branch> getAllBranches() {
        return List.copyOf(branches);
    }

    @Override
    public List<Vehicle> getAllVehicles(final Branch branch, final VehicleType type) {
        List<Vehicle> vehicles = branchToVehicleMap.getOrDefault(branch.getId(), new ArrayList<>());
        return vehicles.stream().filter(vehicle -> vehicle.getVehicleType() == type).toList();
    }

    @Override
    public void addVehicle(final Branch branch, final Vehicle vehicle) {
        List<Vehicle> vehicles = branchToVehicleMap.computeIfAbsent(branch.getId(), (key) -> new ArrayList<>());
        vehicles.add(vehicle);
    }

    @Override
    public List<Vehicle> getAllVehicles(Branch branch) {
        return branchToVehicleMap.getOrDefault(branch.getId(), new ArrayList<>());
    }
}
