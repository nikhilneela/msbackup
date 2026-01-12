package criteria;

import filter.ComparableSimpleFilterValue;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.function.Function;
@AllArgsConstructor
public class GreaterThanFilterCriteria<T extends Comparable<T>> implements IFilterCriteria {
    private final ComparableSimpleFilterValue<T> target;
    private final Function<Product, T> extractor;

    @Override
    public boolean matches(Product product) {
        T extracted = extractor.apply(product);
        return extracted.compareTo(target.getValue()) > 0;
    }
}
