package criteria;

import filter.RangeFilterValue;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.function.Function;

@AllArgsConstructor
public class RangeFilterCriteria<T extends Comparable<T>> implements IFilterCriteria{
    private final RangeFilterValue<T> valueRange;
    private final Function<Product, T> extractor;

    @Override
    public boolean matches(Product product) {
        T extracted = extractor.apply(product);
        return extracted.compareTo(valueRange.getMax()) <= 0 && extracted.compareTo(valueRange.getMin()) >= 0;
    }
}
