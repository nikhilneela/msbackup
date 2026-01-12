package criteria;

import lombok.AllArgsConstructor;
import model.Product;

@AllArgsConstructor
public class NotFilterCriteria implements IFilterCriteria {
    private final IFilterCriteria inner;

    @Override
    public boolean matches(Product product) {
        return !inner.matches(product);
    }
}
