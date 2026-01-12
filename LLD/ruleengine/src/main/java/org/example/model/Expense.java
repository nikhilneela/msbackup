package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Expense {
    private final String id;
    private final ExpenseType type;
    private final String itemId;
    private final Price price;
    private final Seller seller;
}
