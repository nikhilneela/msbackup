package org.example.filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RangeFilterValue<T> implements IFilterValue<Range<T>>{
    private final Range<T> range;

    @Override
    public Range<T> getValue() {
        return range;
    }
}
