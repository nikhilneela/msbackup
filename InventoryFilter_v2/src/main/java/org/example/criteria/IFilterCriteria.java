package org.example.criteria;

import org.example.model.Product;

public interface IFilterCriteria {
    boolean matches(Product product);
}
