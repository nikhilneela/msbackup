package criteria;

import filter.IFilterValue;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.Objects;
import java.util.function.Function;

@AllArgsConstructor
public class EqualsFilterCriteria<T> implements IFilterCriteria {
    private final IFilterValue<T> target;
    private final Function<Product, T> extractor;


    @Override
    public boolean matches(Product product) {
        T extracted = extractor.apply(product);
        return Objects.equals(extracted, target.getValue());
    }
}
