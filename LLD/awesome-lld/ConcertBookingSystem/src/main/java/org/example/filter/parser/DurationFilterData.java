package org.example.filter.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DurationFilterData implements IFilterData<Integer> {
    private Integer value;

    @Override
    public Integer getData() {
        return value;
    }
}
