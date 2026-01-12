package org.example.repository;

import org.example.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryBidRepository implements IBidRepository {
    private final List<Bid> bids;

    public InMemoryBidRepository() {
        this.bids = new ArrayList<>();
    }
    @Override
    public Bid CreateBid(Buyer buyer, Auction auction, Value value) {
        Bid bid = new Bid(UUID.randomUUID().toString(), buyer, auction, value, BidStatus.ACTIVE);
        this.bids.add(bid);
        return bid;
    }

    @Override
    public Bid getBid(String id) {
        return bids.stream().filter(bid -> bid.getId().equals(id)).findFirst().orElse(null);
    }
}
