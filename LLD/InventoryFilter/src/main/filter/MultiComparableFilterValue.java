package filter;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MultiComparableFilterValue<T extends Comparable<T>> implements IFilterValue<List<T>> {
    private final List<T> values;

    @Override
    public List<T> getValue() {
        return values;
    }
}
