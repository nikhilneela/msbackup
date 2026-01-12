package criteria;

import filter.MultiComparableFilterValue;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.function.Function;

@AllArgsConstructor
public class EqualsMultiFilterCriteria<T extends Comparable<T>> implements IFilterCriteria{
    private final MultiComparableFilterValue<T> target;
    private final Function<Product, T> extractor;

    @Override
    public boolean matches(Product product) {
        T value = extractor.apply(product);
        return target.getValue().stream().anyMatch(x -> x.compareTo(value) == 0);
    }
}
