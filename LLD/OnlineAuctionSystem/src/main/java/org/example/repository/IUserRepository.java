package org.example.repository;

import lombok.NonNull;
import org.example.model.Buyer;
import org.example.model.Seller;
import org.example.model.Value;

public interface IUserRepository {
    Seller addSeller(@NonNull final String name);
    Buyer addBuyer(@NonNull final String name, final Value value);
    Buyer getBuyer(@NonNull final String name);
    Seller getSeller(@NonNull final String name);
}
