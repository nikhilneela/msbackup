package org.example.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.example.filter.Filter;
import org.example.filter.parser.FilterDataParser;
import org.example.model.Concert;
import org.example.service.ConcertsSearchService;

import java.util.List;

@AllArgsConstructor
public class ConcertsSearchController {
    private FilterDataParser parser;
    private ConcertsSearchService service;

    @SneakyThrows
    public List<Concert> search(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.reader().readTree(json);
        JsonNode filtersNode = root.get("filters");
        List<Filter<?>> filters = parser.parse(filtersNode);
        return service.search(filters);
    }
}
