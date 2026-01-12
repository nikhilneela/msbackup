package org.example.filter.strategy;

import org.example.filter.FilterType;
import org.example.filter.parser.IFilterDataParser;

import java.util.HashMap;

public class FilterStrategyRegistry {
    private final HashMap<FilterType, IFilterStrategy> strategies;

    public FilterStrategyRegistry() {
        this.strategies = new HashMap<>();
    }

    public void register(FilterType filterType, IFilterStrategy strategy) {
        if (!strategy.matches(filterType)) {
            throw new RuntimeException("Filter type does not match with parser");
        }
        strategies.put(filterType, strategy);
    }

    public IFilterStrategy getStrategy(FilterType filterType) {
        if (!strategies.containsKey(filterType)) {
            throw new RuntimeException("Cannot find parser for filter type");
        }
        return strategies.get(filterType);
    }

    public boolean doesSupport(FilterType filterType) {
        return strategies.containsKey(filterType);
    }
}
