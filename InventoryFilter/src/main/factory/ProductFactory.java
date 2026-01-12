package factory;
import model.Product;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ProductFactory {

    public static List<Product> generateHardcodedProducts() {
        List<Product> products = new ArrayList<>();

        products.add(new Product("P001", "Refurbished iPhone 15 Pro", "Electronics", 999.99,
                new GregorianCalendar(2025, Calendar.JANUARY, 5).getTime(), 4.8,
                "Apple flagship phone, refurbished model with warranty", 50,
                List.of("smartphone", "ios", "refurbished")));

        products.add(new Product("P002", "MacBook Pro 16\"", "Electronics", 2399.00,
                new GregorianCalendar(2025, Calendar.FEBRUARY, 10).getTime(), 4.7,
                "High-performance laptop for professionals", 30,
                List.of("laptop", "mac", "developer")));

        products.add(new Product("P003", "Samsung Galaxy S23", "Electronics", 799.50,
                new GregorianCalendar(2025, Calendar.MARCH, 3).getTime(), 4.6,
                "Samsung's latest Android smartphone", 75,
                List.of("smartphone", "android", "samsung")));

        products.add(new Product("P004", "Dell XPS 13", "Electronics", 1199.99,
                new GregorianCalendar(2025, Calendar.APRIL, 18).getTime(), 4.5,
                "Compact and powerful Windows ultrabook", 40,
                List.of("laptop", "windows", "dell")));

        products.add(new Product("P005", "Nike Air Zoom Pegasus", "Footwear", 120.00,
                new GregorianCalendar(2025, Calendar.MAY, 25).getTime(), 4.4,
                "Comfortable running shoes with Zoom cushioning", 100,
                List.of("shoes", "running", "nike")));

        products.add(new Product("P006", "Adidas Ultraboost", "Footwear", 180.00,
                new GregorianCalendar(2025, Calendar.JUNE, 12).getTime(), 4.3,
                "Premium sports shoes with boost sole", 80,
                List.of("shoes", "adidas", "sports")));

        products.add(new Product("P007", "The Pragmatic Programmer", "Books", 45.00,
                new GregorianCalendar(2025, Calendar.JULY, 1).getTime(), 4.9,
                "Classic software engineering book", 200,
                List.of("book", "software", "engineering")));

        products.add(new Product("P008", "Ergonomic Office Chair", "Furniture", 250.00,
                new GregorianCalendar(2025, Calendar.JULY, 15).getTime(), 4.2,
                "Chair with lumbar support and mesh back", 60,
                List.of("chair", "ergonomic", "office")));

        products.add(new Product("P009", "Standing Desk", "Furniture", 499.99,
                new GregorianCalendar(2025, Calendar.AUGUST, 8).getTime(), 4.4,
                "Adjustable height standing desk", 25,
                List.of("desk", "standing", "furniture")));

        products.add(new Product("P010", "Refurbished Instant Pot Duo", "Appliances", 89.99,
                new GregorianCalendar(2025, Calendar.SEPTEMBER, 20).getTime(), 4.6,
                "7-in-1 electric cooker (refurbished unit)", 150,
                List.of("kitchen", "appliance", "refurbished")));

        products.add(new Product("P011", "Kindle Paperwhite", "Electronics", 129.99,
                new GregorianCalendar(2025, Calendar.OCTOBER, 11).getTime(), 4.7,
                "E-reader with high-resolution display", 90,
                List.of("ebook", "kindle", "reading")));

        products.add(new Product("P012", "GoPro HERO11", "Electronics", 399.99,
                new GregorianCalendar(2025, Calendar.NOVEMBER, 2).getTime(), 4.5,
                "Waterproof 5.3K action camera", 35,
                List.of("camera", "action", "gopro")));

        products.add(new Product("P013", "Fitbit Charge 5", "Accessories", 149.95,
                new GregorianCalendar(2025, Calendar.NOVEMBER, 30).getTime(), 4.3,
                "Fitness tracker (refurbished and tested)", 45,
                List.of("fitness", "wearable", "refurbished")));

        products.add(new Product("P014", "Yoga Mat", "Sports", 30.00,
                new GregorianCalendar(2025, Calendar.DECEMBER, 15).getTime(), 4.1,
                "Eco-friendly non-slip yoga mat", 120,
                List.of("yoga", "fitness", "mat")));

        products.add(new Product("P015", "Herman Miller Cushion", "Accessories", 85.00,
                new GregorianCalendar(2025, Calendar.DECEMBER, 25).getTime(), 4.6,
                "Ergonomic seat cushion for long hours", 70,
                List.of("office", "comfort", "seat")));

        // Clearance items
        products.add(new Product("P016", "Clearance - Lenovo ThinkPad", "Clearance", 199.99,
                new GregorianCalendar(2025, Calendar.JANUARY, 10).getTime(), 4.0,
                "Refurbished ThinkPad with minor scratches", 20,
                List.of("clearance", "refurbished", "laptop")));

        products.add(new Product("P017", "Clearance - Bluetooth Speaker", "Clearance", 25.99,
                new GregorianCalendar(2025, Calendar.FEBRUARY, 18).getTime(), 3.9,
                "Last season model, fully functional", 35,
                List.of("clearance", "audio", "portable")));

        products.add(new Product("P018", "Clearance - Wooden Bookshelf", "Clearance", 99.99,
                new GregorianCalendar(2025, Calendar.MARCH, 5).getTime(), 4.2,
                "Solid wood shelf with open-box discount", 12,
                List.of("clearance", "furniture", "bookshelf")));

        products.add(new Product("P019", "Standing Desk", "Furniture", 89.50,
                new GregorianCalendar(2025, Calendar.MARCH, 5).getTime(), 4.2,
                "Ergonomic Standing desk", 12,
                List.of("clearance", "furniture", "bookshelf")));


        return products;
    }
}
