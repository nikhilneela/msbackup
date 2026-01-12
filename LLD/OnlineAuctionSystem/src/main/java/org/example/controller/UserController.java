package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.example.model.Value;
import org.example.service.UserService;

@AllArgsConstructor
public class UserController {
    private final UserService userService;

    public Buyer addBuyer(@NonNull final String name) {
        return this.userService.addBuyer(name);
    }

    public Seller addSeller(@NonNull final String name) {
        return this.userService.addSeller(name);
    }

    public Buyer addBuyer(@NonNull final String name, @NonNull final Value value) {
        return this.userService.addBuyer(name, value);
    }
}
