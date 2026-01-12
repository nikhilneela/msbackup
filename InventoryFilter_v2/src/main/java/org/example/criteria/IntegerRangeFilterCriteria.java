package org.example.criteria;

import lombok.AllArgsConstructor;
import org.example.filter.IntegerRange;
import org.example.model.Product;

import java.util.function.Function;

@AllArgsConstructor
public class IntegerRangeFilterCriteria implements IFilterCriteria {
    private final IntegerRange target;
    private final Function<Product, Integer> extractor;

    @Override
    public boolean matches(Product product) {
        Integer value = extractor.apply(product);
        return value >= target.getMin() && value <= target.getMax();
    }
}
