package org.example.filter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.filter.FilterType;

public interface IFilterDataParser {
    IFilterData<?> parse(JsonNode jsonNode);
    FilterType getFilterType();
}
