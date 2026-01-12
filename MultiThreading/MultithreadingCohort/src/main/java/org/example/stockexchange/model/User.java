package org.example.stockexchange.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class User {
    @Getter
    private final String id;
    private final String name;
    //can write a separate model class to represent a phoneNumber entity, add necessary sub entities (like country code) & business validations
    private final String phoneNumber;
    private final String emailId;
}
