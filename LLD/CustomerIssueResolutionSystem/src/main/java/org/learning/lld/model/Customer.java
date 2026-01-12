package org.learning.lld.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Customer {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String email;
}
