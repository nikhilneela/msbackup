package criteria;

import filter.MultiValueFilter;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor
public class AllFilterCriteria implements IFilterCriteria {
    private final MultiValueFilter target;
    private final Function<Product, List<String>> extractor;

    @Override
    public boolean matches(Product product) {
        List<String> extractedValues = extractor.apply(product);
        List<String> targetValues = target.getValue();
        for (String targetValue : targetValues) {
            if (extractedValues.stream().noneMatch(v -> v.equalsIgnoreCase(targetValue))) {
                return false;
            }
        }
        return true;
    }
}
