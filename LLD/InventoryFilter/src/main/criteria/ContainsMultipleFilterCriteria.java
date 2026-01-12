package criteria;

import filter.MultiValueFilter;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.function.Function;
@AllArgsConstructor
public class ContainsMultipleFilterCriteria implements IFilterCriteria {
    private final MultiValueFilter target;
    private final Function<Product, String> extractor;

    @Override
    public boolean matches(Product product) {
        String extracted = extractor.apply(product);
        return target.getValue().stream().anyMatch(extracted::equalsIgnoreCase);
    }
}
