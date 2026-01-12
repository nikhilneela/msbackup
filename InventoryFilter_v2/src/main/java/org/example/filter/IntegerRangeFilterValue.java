package org.example.filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntegerRangeFilterValue implements IFilterValue<IntegerRange> {
    private final IntegerRange range;

    @Override
    public IntegerRange getValue() {
        return range;
    }
}
