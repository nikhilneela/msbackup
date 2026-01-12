package org.example.filter.parser;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DateRangeFilterData implements IFilterData<DateRange> {
    private DateRange range;
    @Override
    public DateRange getData() {
        return range;
    }
}
