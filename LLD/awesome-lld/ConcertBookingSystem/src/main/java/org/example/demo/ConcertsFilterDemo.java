package org.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.filter.Filter;
import org.example.filter.FilterType;
import org.example.filter.parser.*;

import java.util.List;
import java.util.stream.Collectors;

public class ConcertsFilterDemo {
    @SneakyThrows
    public static void main(String[] args) {
        String json = """
                {
                  "filters": [
                    {
                      "filterName": "ARTIST",
                      "filterValue": ["rehman", "rafi", "kishore"]
                    },
                    {
                      "filterName": "DURATION",
                      "filterValue": 3
                    },
                    {
                      "filterName": "DATE",
                      "filterValue": {
                        "start": "2025-10-21",
                        "end": "2025-10-30"
                      }
                    }
                  ]
                }
        """;

        FilterDataParserRegistry registry = new FilterDataParserRegistry();
        registry.register(FilterType.ARTIST, new ArtistFilterDataParser());
        registry.register(FilterType.DURATION, new DurationFilterDataParser());
        registry.register(FilterType.DATE, new DateRangeDataParser());

        FilterDataParser parser = new FilterDataParser(registry);

        List<Filter<?>> filterData = parser.parse(new ObjectMapper().readTree(json).get("filters"));

        for (Filter<?> filter : filterData) {
            if (filter.getData() instanceof ArtistFilterData artistFilterData) {
                String result = String.join(",", artistFilterData.getData());
                System.out.println(result);
            } else if (filter.getData() instanceof DurationFilterData durationFilterData) {
                System.out.println(durationFilterData.getData());
            } else if (filter.getData() instanceof DateRangeFilterData dateRangeFilterData) {
                System.out.println(dateRangeFilterData.getData().getStart());
                System.out.println(dateRangeFilterData.getData().getEnd());
            }
        }
    }
}
