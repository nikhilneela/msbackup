package org.example.service;

import lombok.NonNull;
import org.example.model.Budget;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.example.model.Value;
import org.example.repository.IUserRepository;

public class UserService {
    private final IUserRepository userRepository;

    public UserService(@NonNull final IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Buyer addBuyer(@NonNull final String name) {
        Buyer buyer = this.userRepository.getBuyer(name);
        if (buyer == null) {
            buyer = this.userRepository.addBuyer(name, null);
        }
        return buyer;
    }

    public Buyer addBuyer(@NonNull final String name, @NonNull final Value value) {
        Buyer buyer = this.userRepository.getBuyer(name);
        if (buyer == null) {
            buyer = this.userRepository.addBuyer(name, value);
        }
        return buyer;
    }

    public Seller addSeller(@NonNull final String name) {
        Seller seller = this.userRepository.getSeller(name);
        if (seller == null) {
            seller = this.userRepository.addSeller(name);
        }
        return seller;
    }

    public Seller getSeller(@NonNull final String name) {
        return this.userRepository.getSeller(name);
    }

    public Buyer getBuyer(@NonNull final String name) {
        return this.userRepository.getBuyer(name);
    }

    public void updateBudget(@NonNull final String buyerId, Value value) {
        Buyer buyer = this.userRepository.getBuyer(buyerId);
        buyer.updateBudget(value);
    }
}
