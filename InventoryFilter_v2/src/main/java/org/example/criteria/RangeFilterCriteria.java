package org.example.criteria;

import lombok.AllArgsConstructor;
import org.example.filter.IFilterValue;
import org.example.filter.Range;
import org.example.model.Product;

import java.util.function.Function;

@AllArgsConstructor
public class RangeFilterCriteria<T> implements IFilterCriteria {
    private final IFilterValue<Range<T>> target;
    private final Function<Product, T> extractor;

    @Override
    public boolean matches(Product product) {
        T value = extractor.apply(product);
        //confused ?? how to compare ranges ?? :(
    }
}
