package org.example.service;

import lombok.AllArgsConstructor;
import org.example.filter.strategy.IFilterStrategy;
import org.example.model.InputFilter;
import org.example.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class BookingService {
    private final List<IFilterStrategy> filterStrategies;
    private final VehicleService vehicleService;

    public boolean bookVehicle(List<InputFilter> filters) {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();

        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (InputFilter filter : filters) {
            for (IFilterStrategy vehicleFilterStrategy : filterStrategies) {
                if (vehicleFilterStrategy.doesSupport(filter)) {
                    List<Vehicle> _v = vehicleFilterStrategy.apply(filter, allVehicles);
                    filteredVehicles.addAll(_v);
                }
            }
        }
        return true;
    }
}
