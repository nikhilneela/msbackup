package criteria;

import model.Product;

public interface IFilterCriteria {
    boolean matches(Product product);
}
