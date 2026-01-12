package engine;

import criteria.IFilterCriteria;
import model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductFilterEngine {
    public List<Product> getProducts(List<Product> products, IFilterCriteria filterCriteria) {
        return products.stream().filter(filterCriteria::matches).collect(Collectors.toList());
    }
}
