package filter;

import lombok.Getter;

@Getter
public class Range<T extends Comparable<T>> {
    private final T min;
    private final T max;

    public Range(T min, T max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("");
        }
        this.min = min;
        this.max = max;
    }
}
