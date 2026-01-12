package org.example.filter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.example.filter.Filter;
import org.example.filter.FilterCondition;
import org.example.filter.FilterType;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FilterDataParser {
    private FilterDataParserRegistry registry;

    public List<Filter<?>> parse(JsonNode inputNode) {
        List<Filter<?>> filters = new ArrayList<>();
        if (!inputNode.isArray()) {
            FilterType type = FilterType.valueOf(inputNode.get("filterName").asText());
            FilterCondition condition = FilterCondition.EQUALS;
            if (inputNode.get("filterCondition") != null) {
                condition = FilterCondition.valueOf(inputNode.get("filterCondition").asText());
            }
            filters.add(new Filter<>(registry.getParser(type).parse(inputNode), type, condition));
        } else {
            for (JsonNode node : inputNode) {
                FilterType type = FilterType.valueOf(node.get("filterName").asText());
                FilterCondition condition = FilterCondition.EQUALS;
                if (node.get("filterCondition") != null) {
                    condition = FilterCondition.valueOf(node.get("filterCondition").asText());
                }
                filters.add(new Filter<>(registry.getParser(type).parse(node), type, condition));
            }
        }
        return filters;
    }
}
