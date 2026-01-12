package org.example.filter.parser;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.filter.FilterType;

import java.util.ArrayList;
import java.util.List;

public class ArtistFilterDataParser implements IFilterDataParser {
    @Override
    public ArtistFilterData parse(JsonNode jsonNode) {
        FilterType type = FilterType.valueOf(jsonNode.get("filterName").asText());
        if (type != getFilterType()) {
            throw new RuntimeException("Parsing error");
        }

        JsonNode value = jsonNode.get("filterValue");
        List<String> artists = new ArrayList<>();
        if (value.isArray()) {
            for (JsonNode node : value) {
                artists.add(node.asText());
            }
        } else {
            artists.add(value.asText());
        }

        return new ArtistFilterData(artists);
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.ARTIST;
    }
}
