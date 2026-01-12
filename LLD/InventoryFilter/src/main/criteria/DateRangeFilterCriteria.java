package criteria;

import filter.DateRangeFilterValue;
import lombok.AllArgsConstructor;
import model.Product;

import java.util.Date;
import java.util.function.Function;
@AllArgsConstructor
public class DateRangeFilterCriteria implements IFilterCriteria{
    private final DateRangeFilterValue target;
    private final Function<Product, Date> extractor;

    @Override
    public boolean matches(Product product) {
        Date extracted = extractor.apply(product);
        return extracted.after(target.getValue().getMin()) && extracted.before(target.getValue().getMax());
    }
}
