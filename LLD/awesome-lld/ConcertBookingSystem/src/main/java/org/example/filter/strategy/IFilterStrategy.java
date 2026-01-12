package org.example.filter.strategy;

import org.example.filter.Filter;
import org.example.filter.FilterCondition;
import org.example.filter.FilterType;
import org.example.filter.parser.IFilterData;
import org.example.model.Concert;

import java.util.List;

public interface IFilterStrategy {
    List<Concert> filter(List<Concert> input, Filter<?> filter);
    boolean matches(FilterType type);
    boolean evaluateCondition(Concert concert, IFilterData<?> filterData, FilterCondition condition);
}
