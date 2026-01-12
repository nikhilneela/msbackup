package org.example.filter.parser;

import org.example.filter.FilterType;

import java.util.HashMap;

public class FilterDataParserRegistry {
    private final HashMap<FilterType, IFilterDataParser> parsers;

    public FilterDataParserRegistry() {
        this.parsers = new HashMap<>();
    }

    public void register(FilterType filterType, IFilterDataParser parser) {
        if (parser.getFilterType() != filterType) {
            throw new RuntimeException("Filter type does not match with parser");
        }
        parsers.put(filterType, parser);
    }

    public IFilterDataParser getParser(FilterType filterType) {
        if (!parsers.containsKey(filterType)) {
            throw new RuntimeException("Cannot find parser for filter type");
        }
        return parsers.get(filterType);
    }

    public boolean doesSupport(FilterType filterType) {
        return parsers.containsKey(filterType);
    }
}
