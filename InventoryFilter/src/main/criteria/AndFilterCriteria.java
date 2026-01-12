package criteria;

import lombok.AllArgsConstructor;
import model.Product;

@AllArgsConstructor
public class AndFilterCriteria implements IFilterCriteria {
    private final IFilterCriteria left;
    private final IFilterCriteria right;
    @Override
    public boolean matches(Product product) {
        return left.matches(product) && right.matches(product);
    }
}
