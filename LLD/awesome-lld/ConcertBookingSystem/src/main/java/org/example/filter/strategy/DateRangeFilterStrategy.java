package org.example.filter.strategy;

import org.example.filter.Filter;
import org.example.filter.FilterCondition;
import org.example.filter.FilterType;
import org.example.filter.parser.DateRange;
import org.example.filter.parser.DateRangeFilterData;
import org.example.filter.parser.IFilterData;
import org.example.model.Concert;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DateRangeFilterStrategy implements IFilterStrategy {
    @Override
    public List<Concert> filter(List<Concert> input, Filter<?> filter) {

        return input.stream().filter(
                concert -> evaluateCondition(concert, filter.getData(),filter.getCondition())).toList();
    }

    @Override
    public boolean matches(FilterType type) {
        return type == FilterType.DATE;
    }

    @Override
    public boolean evaluateCondition(Concert concert, IFilterData<?> filterData, FilterCondition condition) {
        DateRange dateRange = ((DateRangeFilterData) filterData).getData();
        LocalDate from = dateRange.getStart();
        LocalDate to = dateRange.getEnd();
        LocalDate concertDate = concert.getTime().toLocalDate();
        if (condition == FilterCondition.IN_BETWEEN) {
            return concertDate.isAfter(from) && concertDate.isBefore(to);
        }
        return false;
    }
}
