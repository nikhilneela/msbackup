package org.example.filter.strategy;

import org.example.filter.Filter;
import org.example.filter.FilterCondition;
import org.example.filter.FilterType;
import org.example.filter.parser.DurationFilterData;
import org.example.filter.parser.IFilterData;
import org.example.model.Concert;

import java.util.List;
import java.util.stream.Collectors;

public class DurationFilterStrategy implements IFilterStrategy {
    @Override
    public List<Concert> filter(List<Concert> input, Filter<?> filter) {
        return input.stream().filter(
                concert -> evaluateCondition(
                        concert,
                        filter.getData(),
                        filter.getCondition()
                )).toList();
    }

    @Override
    public boolean matches(FilterType type) {
        return type == FilterType.DURATION;
    }

    @Override
    public boolean evaluateCondition(Concert concert, IFilterData<?> filterData, FilterCondition condition) {
        DurationFilterData durationFilterData = (DurationFilterData) filterData;

        if (condition == FilterCondition.GREATER_THAN) {
            return concert.getDurationInMinutes() > durationFilterData.getData();
        } else if (condition == FilterCondition.LESS_THAN) {
            return concert.getDurationInMinutes() < durationFilterData.getData();
        }
        return false;
    }
}
