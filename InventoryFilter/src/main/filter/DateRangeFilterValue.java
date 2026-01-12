package filter;

import lombok.AllArgsConstructor;

import java.util.Date;
@AllArgsConstructor
public class DateRangeFilterValue implements IFilterValue<Range<Date>> {
    private final Range<Date> range;
    @Override
    public Range<Date> getValue() {
        return range;
    }
}
