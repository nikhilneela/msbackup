package repository;

import model.Product;

import java.util.List;

public interface IProductsRepository {
    List<Product> getProducts();
}
