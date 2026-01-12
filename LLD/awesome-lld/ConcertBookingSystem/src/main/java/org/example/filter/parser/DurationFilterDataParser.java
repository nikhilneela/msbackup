package org.example.filter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.filter.FilterType;

public class DurationFilterDataParser implements IFilterDataParser {
    @Override
    public DurationFilterData parse(JsonNode jsonNode) {
        FilterType type = FilterType.valueOf(jsonNode.get("filterName").asText());
        if (type != getFilterType()) {
            throw new RuntimeException("Parsing error");
        }
        int duration = jsonNode.get("filterValue").asInt();
        return new DurationFilterData(duration);
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.DURATION;
    }
}
