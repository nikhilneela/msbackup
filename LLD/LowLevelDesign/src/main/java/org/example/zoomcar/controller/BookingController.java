package org.example.zoomcar.controller;

import lombok.AllArgsConstructor;
import org.example.zoomcar.model.Booking;
import org.example.zoomcar.model.TimeSlot;
import org.example.zoomcar.model.VehicleType;
import org.example.zoomcar.service.BookingService;

import java.time.LocalDateTime;

@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    public Booking bookVehicle(final VehicleType type, LocalDateTime start, LocalDateTime end) {
        //todo validations
        // start should be before end
        // hourly granularity
        TimeSlot slot = new TimeSlot(start, end);
        Booking booking = bookingService.createBooking(type, slot);
        if (booking == null) {
            System.out.println("No vehicles of type " + type + " are available between " + slot);
        }
        return booking;
    }
}
