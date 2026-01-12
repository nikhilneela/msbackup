package org.example.filter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.filter.FilterType;

import java.time.LocalDate;

public class DateRangeDataParser implements IFilterDataParser {
    @Override
    public DateRangeFilterData parse(JsonNode jsonNode) {
        FilterType type = FilterType.valueOf(jsonNode.get("filterName").asText());
        if (type != getFilterType()) {
            throw new RuntimeException("Parsing error");
        }
        JsonNode node = jsonNode.get("filterValue");
        LocalDate start = LocalDate.parse(node.get("start").asText());
        LocalDate end = LocalDate.parse(node.get("end").asText());
        return new DateRangeFilterData(new DateRange(start, end));
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.DATE;
    }
}
