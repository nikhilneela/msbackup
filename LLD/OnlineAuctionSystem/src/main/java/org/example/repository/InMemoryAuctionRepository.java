package org.example.repository;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.exception.NoSuchAuctionExists;
import org.example.model.Auction;
import org.example.model.AuctionState;
import org.example.model.Seller;
import org.example.model.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class InMemoryAuctionRepository implements IAuctionRepository {
    private final List<Auction> auctions;
    public InMemoryAuctionRepository() {
        this.auctions = new ArrayList<>();
    }

    @Override
    public Auction CreateAuction(String auctionName, Seller seller, Value highestBid, Value lowestBid) {
        Auction auction = new Auction(UUID.randomUUID().toString(), auctionName, lowestBid, highestBid, seller, AuctionState.OPEN);
        this.auctions.add(auction);
        return auction;
    }

    @Override
    public Auction getAuction(@NonNull final String auctionName) {
        return this.auctions.stream()
                .filter(auction -> auction.getName().equals(auctionName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void closeAuction(@NonNull final String auctionName) {
        Auction auction = this.auctions.stream()
                .filter(a -> a.getName().equals(auctionName))
                .findFirst()
                .orElse(null);
        if (auction == null) {
            throw new NoSuchAuctionExists(auctionName);
        }
        if (auction.getState() != AuctionState.OPEN) {
            throw new IllegalStateException();
        }
        auction.setState(AuctionState.CLOSED);
    }
}
