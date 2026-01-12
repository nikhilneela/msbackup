import criteria.*;
import engine.ProductFilterEngine;
import factory.ProductFactory;
import filter.*;
import model.Product;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {

    public static void print(List<Product> products) {
        for (Product product : products) {
            System.out.println(product.getId() + " | " + product.getName() +  " | " + product.getDescription() + " | " + product.getCategory() + " | " + product.getPrice() + " | " + product.getPurchaseDate() + " | " + product.getRating() + " | " + product.getStock() + " | " + String.join(",", product.getTags()));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        List<Product> sampleProducts = ProductFactory.generateHardcodedProducts();
        ProductFilterEngine engine = new ProductFilterEngine();

        //Get all products with furntiure category
        System.out.println("Get all products with furntiure category");
        IFilterCriteria equalsFilterCriteria = new EqualsFilterCriteria<>(() -> "Furniture", Product::getCategory);
        List<Product> filteredProducts = engine.getProducts(sampleProducts, equalsFilterCriteria);
        print(filteredProducts);

        //Get all products with category contains "electro"
        System.out.println("Get all products with category contains \"electro\"");
        IFilterCriteria containsFilterCriteria = new ContainsFilterCriteria("electro", Product::getCategory);
        filteredProducts = engine.getProducts(sampleProducts, containsFilterCriteria);
        print(filteredProducts);

        //Get all products with category in furniture or electronics
        System.out.println("Get all products with category in furniture or electronics");
        IFilterCriteria containsMultipleFilterCriteria = new ContainsMultipleFilterCriteria(new MultiValueFilter(List.of("electronics", "furniture")), Product::getCategory);
        filteredProducts = engine.getProducts(sampleProducts, containsMultipleFilterCriteria);
        print(filteredProducts);

        //Get all products purchased betwen "2025-01-01" and "2025-04-05"
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");  // ASCII hyphens


        Date start, end;
        try {
            start = fmt.parse("2025-01-01");
            end   = fmt.parse("2025-04-05");
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid date literal", e);
        }

        System.out.println("Get all products purchased betwen \"2025-01-01\" and \"2025-04-05\"");
        DateRangeFilterValue dateRangeValueFilter = new DateRangeFilterValue(new Range<Date>(start, end));
        IFilterCriteria dateRangeFilterCriteria = new DateRangeFilterCriteria(dateRangeValueFilter, Product::getPurchaseDate);
        filteredProducts = engine.getProducts(sampleProducts, dateRangeFilterCriteria);
        print(filteredProducts);

        //Get all products with rating greater than 4.5
        System.out.println("Get all products with rating greater than 4.5");
        ComparableSimpleFilterValue<Double> ratingFilterValue = new ComparableSimpleFilterValue<>(4.5);
        IFilterCriteria greaterThanFilterCriteria = new GreaterThanFilterCriteria<>(ratingFilterValue, Product::getRating);
        filteredProducts = engine.getProducts(sampleProducts, greaterThanFilterCriteria);
        print(filteredProducts);

        //Get all products with rating between 4.0 and 4.3
        System.out.println("Get all products with rating between 4.0 and 4.3");
        RangeFilterValue<Double> ratingRangeFilterValue = new RangeFilterValue<>(4.0, 4.3);
        IFilterCriteria ratingRangeFilterCriteria = new RangeFilterCriteria<>(ratingRangeFilterValue, Product::getRating);
        filteredProducts = engine.getProducts(sampleProducts, ratingRangeFilterCriteria);
        print(filteredProducts);

        //Get all products whose category is electronics and rating is between 4.0 and 4.7
        System.out.println("Get all products whose category is electronics and rating is between 4.0 and 4.7");
        IFilterCriteria equalsFilterCriteria1 = new EqualsFilterCriteria<>(() -> "Electronics", Product::getCategory);
        IFilterCriteria rangeFilterCriteria = new RangeFilterCriteria<Double>(new RangeFilterValue<>(4.0, 4.7), Product::getRating);
        IFilterCriteria andFilterCriteria = new AndFilterCriteria(equalsFilterCriteria1, rangeFilterCriteria);
        filteredProducts = engine.getProducts(sampleProducts, andFilterCriteria);
        print(filteredProducts);

        //Find products whose name or description contains the word “refurbished” (case‑insensitive).
        System.out.println("Find products whose name or description contains the word “refurbished” (case‑insensitive).");
        String target = "refurbished";
        IFilterCriteria containsFilterCriteria1 = new ContainsFilterCriteria(target, Product::getName);
        IFilterCriteria containsFilterCriteria2 = new ContainsFilterCriteria(target, Product::getDescription);
        IFilterCriteria orFilterCriteria = new OrFilterCriteria(containsFilterCriteria1, containsFilterCriteria2);
        filteredProducts = engine.getProducts(sampleProducts, orFilterCriteria);
        print(filteredProducts);

        //Exclude all products in the “Clearance” category.
        System.out.println("Exclude all products in the “Clearance” category.");
        IFilterCriteria notFilterCriteria = new NotFilterCriteria(new ContainsFilterCriteria("clearance", Product::getCategory));
        filteredProducts = engine.getProducts(sampleProducts, notFilterCriteria);
        print(filteredProducts);

        //Include products which have all specified tags
        System.out.println("Include all products that have clearance and furniture as tags");
        IFilterCriteria allFilterCriteria = new AllFilterCriteria(new MultiValueFilter(List.of("clearance", "furniture")), Product::getTags);
        filteredProducts = engine.getProducts(sampleProducts, allFilterCriteria);
        print(filteredProducts);

        //Include products which have any of the specified tags
        System.out.println("Include all products that have clearance or furniture as tags");
        IFilterCriteria anyFilterCriteria = new AnyFilterCriteria(new MultiValueFilter(List.of("clearance", "furniture")), Product::getTags);
        filteredProducts = engine.getProducts(sampleProducts, anyFilterCriteria);
        print(filteredProducts);

        //Include products that have exactly 9.99, 99.99, 199.99 price points
        System.out.println("Include all products that have exact price of 9.99, 99.99, 199.99");
        IFilterCriteria multiEqualsCriteria = new EqualsMultiFilterCriteria<>(new MultiComparableFilterValue<Double>(List.of(9.99, 99.99, 199.99)), Product::getPrice);
        filteredProducts = engine.getProducts(sampleProducts, multiEqualsCriteria);
        print(filteredProducts);



        /*
             filters : [
                {
                    "filterName" : "and",
                    "filterValue" : {
                        "left" : {
                            "filterName" : "price",
                            "filterValue" : "20",
                            "filterCondition" : "lesser"
                        },
                        "right" : {
                            "filterName" : "category",
                            "filterValue" : "furniture",
                            "filterCondition" : "exact"
                        }
                    }
                }
             ]
         */

    }
}
