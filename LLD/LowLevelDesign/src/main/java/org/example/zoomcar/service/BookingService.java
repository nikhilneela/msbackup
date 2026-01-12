package org.example.zoomcar.service;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.*;
import org.example.zoomcar.repository.IBookingRepository;
import org.example.zoomcar.repository.IPricingRepository;
import org.example.zoomcar.repository.InMemoryBookingRepository;
import org.example.zoomcar.strategy.IBookingSelectionStrategy;

import java.util.*;

@AllArgsConstructor
public class BookingService {
    private final IBookingRepository bookingRepository;
    private final IPricingRepository priceRepository;
    private final IBookingSelectionStrategy strategy;

    private BranchService branchService;

    public Booking createBooking(final VehicleType type, final TimeSlot timeSlot) {
        List<Vehicle> availableVehicles = getAvailableVehicles(type, timeSlot);
        if (availableVehicles.isEmpty()) {
            return null;
        }
        //apply strategy to find the minimum price
        Vehicle selectedVehicle = strategy.getSelectedVehicle(availableVehicles);
        //create booking
        Branch selectedBranch = branchService.getBranchById(selectedVehicle.getBranchId());
        return bookingRepository.createBooking(selectedBranch, selectedVehicle, priceRepository.getPrice(selectedBranch, type), timeSlot);
    }

    public List<Vehicle> getAvailableVehicles(final VehicleType type, TimeSlot slot) {
        List<Booking> bookings = bookingRepository.getAllBookings().stream().filter(booking -> booking.getType() == type).toList();

        HashSet<String> overlapping = new HashSet<>();
        bookings.forEach((booking -> {
            if (booking.getSlot().doesOverlap(slot)) {
                overlapping.add(booking.getVehicleRegNumber());
            }
        }));
        return branchService.getAllVehicles(type).stream().filter(vehicle -> !overlapping.contains(vehicle.getRegNumber())).toList();
    }
}
