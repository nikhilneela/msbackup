package criteria;

import lombok.AllArgsConstructor;
import model.Product;

import java.util.function.Function;
@AllArgsConstructor
public class ContainsFilterCriteria implements IFilterCriteria {
    private final String target;
    private final Function<Product, String> extractor;

    @Override
    public boolean matches(Product product) {
        return extractor.apply(product).toLowerCase().contains(target.toLowerCase());
    }
}
