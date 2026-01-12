package org.example.criteria;

import lombok.AllArgsConstructor;
import org.example.filter.IFilterValue;
import org.example.model.Product;

import java.util.function.Function;

@AllArgsConstructor
public class EqualsFilterCriteria<T> implements IFilterCriteria {
    private final IFilterValue<T> target;
    private final Function<Product, T> extractor;

    @Override
    public boolean matches(Product product) {
        T value = extractor.apply(product);
        return value == target;
    }
}
