package org.example.filter.parser;

import lombok.AllArgsConstructor;
import org.example.filter.strategy.IFilterStrategy;
import org.example.model.InputFilter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class InputFilterParser {
    private final Map<String, IFilterStrategy> filterStrategyMap;
    /*
        {
            filters : [
                {
                    filterName : "VEHICLE_TYPE",
                    filterValue : "SEDAN"
                },
                {
                    filterName : "TIMESLOT",
                    filterValue : {
                        start : "2025-11-22 10:00 AM",
                        end : "2025-11-22 5:00 PM"
                    }
                },
                and : {
                    {
                        filterName : "TRANSMISSION_TYPE",
                        filterValue : "ELECTRIC"
                    },
                    {
                        filterName : "RANGE",
                        filterValue : "500KMS"
                    }
                }

            ]
        }
     */
    List<InputFilter> parse(final String inputFilterJson) {
        JSONObject jsonObject = new JSONObject(inputFilterJson);
        JSONArray filters = jsonObject.getJSONArray("filters");
        final List<InputFilter> parsedFilters = new ArrayList<>();
        for (int i = 0; i < filters.length(); i++) {
            JSONObject filterJson = filters.getJSONObject(i);
            final String filterName = filterJson.getString("filterName");
            final IFilterStrategy filterStrategy = filterStrategyMap.get(filterName);
            InputFilter inputFilter = filterStrategy.parse(filterJson.getString("filterValue"));
            parsedFilters.add(inputFilter);
        }
        return parsedFilters;
    }
}
