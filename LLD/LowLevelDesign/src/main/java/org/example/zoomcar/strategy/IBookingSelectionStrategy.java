package org.example.zoomcar.strategy;

import org.example.zoomcar.model.Vehicle;

import java.util.List;

public interface IBookingSelectionStrategy {
    Vehicle getSelectedVehicle(List<Vehicle> vehicles);
}
