package org.example.filter.filterdata;

import lombok.AllArgsConstructor;
import org.example.model.VehicleType;

@AllArgsConstructor
public class VehicleTypeFilterData implements IFilterData {
    private VehicleType type;
}
