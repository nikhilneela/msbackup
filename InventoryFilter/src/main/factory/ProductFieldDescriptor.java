package factory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import model.Product;

import java.util.function.Function;

@AllArgsConstructor
@Getter
public class ProductFieldDescriptor<T> {
    private final String fieldName;
    private final Class<T> fieldType;
    private final Function<Product, T> extractor;
}
