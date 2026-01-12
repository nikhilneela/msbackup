package org.example.filter.strategy;

import org.example.filter.filterdata.AndFilterData;
import org.example.model.FilterType;
import org.example.model.InputFilter;
import org.example.model.Vehicle;

import java.util.List;
import java.util.Map;

public class AndFilterStrategy implements IFilterStrategy {
    private Map<String, IFilterStrategy> filterStrategyMap;

    @Override
    public boolean doesSupport(InputFilter filter) {
        return filter.getType() == FilterType.AND;
    }

    @Override
    public List<Vehicle> apply(InputFilter filter, List<Vehicle> vehicles) {
        AndFilterData andFilter = (AndFilterData) filter.getFilterData();
        InputFilter leftFilter = andFilter.getLeft();
        InputFilter rightFilter = andFilter.getRight();
        leftFilter.
        return List.of();
    }

    @Override
    public InputFilter parse(String json) {
        return null;
    }
}
