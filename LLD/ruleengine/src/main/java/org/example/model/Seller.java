package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Seller {
    private final String id;
    private final String name;
    private final SellerType type;
}
