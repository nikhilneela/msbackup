package filter;

import lombok.Getter;

//can be used for all types which are ranges
@Getter
public class RangeFilterValue<T extends Comparable<T>> implements IFilterValue<Range<T>>{
    private final T min;
    private final T max;

    public RangeFilterValue(final T min, final T max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("");
        }
        this.min = min;
        this.max = max;
    }

    @Override
    public Range<T> getValue() {
        return null;
    }
}
