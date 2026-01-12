package org.example.zoomcar.service;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.*;
import org.example.zoomcar.repository.IBookingRepository;
import org.example.zoomcar.repository.IBranchRepository;
import org.example.zoomcar.repository.IPricingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class BranchService {
    private final IBranchRepository repository;
    private final IPricingRepository priceRepository;
    private final IBookingRepository bookingRepository;

    public Branch getBranch(String branchName) {
        return repository.getBranch(branchName);
    }

    public Branch addBranch(String branchName) {
        return repository.addBranch(branchName);
    }

    public List<Vehicle> getAllVehicles(final VehicleType type) {
        List<Vehicle> vehicles = new ArrayList<>();
        List<Branch> branches = repository.getAllBranches();
        for (Branch branch : branches) {
            vehicles.addAll(repository.getAllVehicles(branch, type));
        }
        return vehicles;
    }

    public double getPrice(final String branchName, final VehicleType type) {
        Branch branch = getBranch(branchName);
        return priceRepository.getPrice(branch, type);
    }

    public Branch getBranchById(final String branchId) {
        return repository.getAllBranches().stream().filter(branch -> branch.getId().equals(branchId)).findFirst().orElse(null);
    }

    public void addPrice(final Branch branch, final VehicleType type, final double price) {
        priceRepository.addPrice(branch, type, price);
    }

    public void addVehicle(final String branchName, final VehicleType type, final String regNumber) {
        Branch branch = repository.getBranch(branchName);
        Vehicle vehicle = new Vehicle(UUID.randomUUID().toString(), regNumber, type, branch.getId());
        repository.addVehicle(branch, vehicle);
    }

    public List<Vehicle> getAllVehicles(final String branchName) {
        Branch branch = repository.getBranch(branchName);
        return repository.getAllVehicles(branch);
    }

    public List<InventoryInfo> getInventoryInfo(TimeSlot slot) {
        List<Branch> branches = repository.getAllBranches();
        return branches.stream().map(branch -> {
            List<Vehicle> vehicles = getAllVehicles(branch.getName());
            List<VehicleState> vehicleStates = vehicles.stream().map(vehicle -> new VehicleState(vehicle.getRegNumber(), !isVehicleBooked(vehicle.getRegNumber(), slot))).toList();
            return new InventoryInfo(branch.getName(), vehicleStates);
        }).toList();
    }

    public boolean isVehicleBooked(final String vehicleRegNumber, TimeSlot slot) {
        List<Booking> bookings = bookingRepository.getAllBookings().stream().filter(booking -> booking.getVehicleRegNumber().equals(vehicleRegNumber)).toList();
        if (bookings.isEmpty()) {
            return false;
        }
        return bookings.stream().anyMatch(booking -> booking.getSlot().doesOverlap(slot));
    }
}
