package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
public class Product {
    private String id;
    private String name;
    private String category;
    private Double price;
    private Date purchaseDate;
    private Double rating;
    private String description;
    private int stock;
    private List<String> tags;
}
