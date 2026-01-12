package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class Auction {
    private final String id;
    private String name;
    private Value minValue;
    private Value maxValue;
    private Seller seller;
    @Setter
    private AuctionState state;

    public Auction(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Auction objAuction)) return false;
        return Objects.equals(this.id, objAuction.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
