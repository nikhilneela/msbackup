package org.example.zoomcar.repository;

import org.example.zoomcar.model.*;

import java.util.*;

public class InMemoryBookingRepository implements IBookingRepository {
    private final Map<String, List<Booking>> dayToBookingsMap;
    private final List<Booking> bookings;

    public InMemoryBookingRepository() {
        dayToBookingsMap = new HashMap<>();
        bookings = new ArrayList<>();
    }

    @Override
    public Booking createBooking(Branch branch, Vehicle vehicle, double price, TimeSlot timeSlot) {
        Booking booking = new Booking(UUID.randomUUID().toString(), timeSlot, vehicle.getVehicleType(), branch.getName(), price, vehicle.getRegNumber());
        bookings.add(booking);
        return booking;
    }

    @Override
    public List<Booking> getAllBookings(TimeSlot slot) {
        String key = buildKey(slot);
        return dayToBookingsMap.getOrDefault(key, new ArrayList<>());
    }

    private String buildKey(TimeSlot timeSlot) {
        return timeSlot.getFrom().getDayOfYear() + "#" + timeSlot.getFrom().getHour();
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookings;
    }
}
