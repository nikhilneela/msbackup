package filter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ComparableSimpleFilterValue<T extends Comparable<T>> implements IFilterValue<T>{
    private final T value;
    @Override
    public T getValue() {
        return value;
    }
}
