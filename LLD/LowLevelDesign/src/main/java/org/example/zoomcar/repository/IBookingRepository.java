package org.example.zoomcar.repository;

import org.example.zoomcar.model.*;

import java.util.List;

public interface IBookingRepository {
    Booking createBooking(Branch branch, Vehicle vehicle, double price, TimeSlot timeSlot);
    List<Booking> getAllBookings(TimeSlot slot);
    List<Booking> getAllBookings();
}
