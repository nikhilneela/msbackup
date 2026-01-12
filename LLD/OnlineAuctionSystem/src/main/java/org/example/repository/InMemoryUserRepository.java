package org.example.repository;

import lombok.NonNull;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.example.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryUserRepository implements IUserRepository {
    private final List<Seller> sellers;
    private final List<Buyer> buyers;

    public InMemoryUserRepository() {
        this.buyers = new ArrayList<>();
        this.sellers = new ArrayList<>();
    }

    @Override
    public Seller addSeller(@NonNull final String name) {
        Seller seller = new Seller(UUID.randomUUID().toString(), name);
        this.sellers.add(seller);
        return seller;
    }

    @Override
    public Buyer addBuyer(@NonNull final String name, final Value value) {
        Buyer buyer = new Buyer(UUID.randomUUID().toString(), name, value);
        this.buyers.add(buyer);
        return buyer;
    }

    @Override
    public Buyer getBuyer(@NonNull String name) {
        return this.buyers.stream().filter(buyer -> buyer.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public Seller getSeller(@NonNull String name) {
        return this.sellers.stream().filter(seller -> seller.getName().equals(name)).findFirst().orElse(null);
    }
}
