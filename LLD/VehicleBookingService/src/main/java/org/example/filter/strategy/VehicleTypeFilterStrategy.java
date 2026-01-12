package org.example.filter.strategy;

import org.example.filter.filterdata.VehicleTypeFilterData;
import org.example.model.FilterType;
import org.example.model.InputFilter;
import org.example.model.Vehicle;
import org.example.model.VehicleType;

import java.util.List;

public class VehicleTypeFilterStrategy implements IFilterStrategy {

    @Override
    public boolean doesSupport(InputFilter filter) {
        return filter.getType() == FilterType.VEHICLE_TYPE;
    }

    @Override
    public List<Vehicle> apply(InputFilter filter, List<Vehicle> vehicles) {
        return List.of();
    }

    @Override
    public InputFilter parse(String json) {
       VehicleType vehicleType = VehicleType.valueOf(json);
       return new InputFilter(FilterType.VEHICLE_TYPE, new VehicleTypeFilterData(vehicleType));
    }
}
