package org.example.service;

import lombok.NonNull;
import org.example.exception.BidCannotBePlaced;
import org.example.exception.NoSuchAuctionExists;
import org.example.exception.NoSuchBidExistsException;
import org.example.model.*;
import org.example.repository.IBidRepository;
import org.example.strategy.IAuctionWinnerDecisionStrategy;

import java.util.*;

public class BidService {
    private final HashMap<Auction, List<Bid>> auctionBidMap;
    private final IBidRepository bidRepository;
    private final IAuctionWinnerDecisionStrategy auctionWinnerDecisionStrategy;

    public BidService(@NonNull final IBidRepository bidRepository, IAuctionWinnerDecisionStrategy auctionWinnerDecisionStrategy) {
        this.bidRepository = bidRepository;
        this.auctionWinnerDecisionStrategy = auctionWinnerDecisionStrategy;
        this.auctionBidMap = new HashMap<>();
    }

    public Bid placeBid(Buyer buyer, Auction auction, Value value) {
        if (!buyer.canPlaceBid(value)) {
            throw new BidCannotBePlaced(String.format("Buyer %s has budget %s and bid value is %s", buyer.getName(), buyer.getBudget().getValue(), value));
        }

        if (!value.isBetweenInclusive(auction.getMinValue(), auction.getMaxValue())) {
            throw new BidCannotBePlaced(String.format("Bid value must be within [%s, %s] range", auction.getMinValue(), auction.getMaxValue()));
        }

        Bid bid = bidRepository.CreateBid(buyer, auction, value);
        auctionBidMap.computeIfAbsent(auction, k -> new ArrayList<>()).add(bid);
        return bid;
    }

    public boolean updateBid(@NonNull final String id, Value value) {
        Bid bid = bidRepository.getBid(id);
        if (bid == null) {
            throw new NoSuchBidExistsException();
        }
        return bid.updateValue(value);
    }

    public void withdrawBid(@NonNull final String id) {
        Bid bid = bidRepository.getBid(id);
        if (bid == null) {
            throw new NoSuchBidExistsException();
        }
        bid.setStatus(BidStatus.WITHDRAWN);
    }

    public Bid getWinningBid(Auction auction) {
        if (!auctionBidMap.containsKey(auction)) {
            throw new NoSuchAuctionExists(auction.getName());
        }
        List<Bid> bids = auctionBidMap.get(auction);
        return auctionWinnerDecisionStrategy.getWinnigBid(bids);
    }

    public Bid getBid(@NonNull final String buyerName, @NonNull final Auction auction) {
        List<Bid> bids = auctionBidMap.getOrDefault(auction, new ArrayList<>());
        return bids.stream().filter(bid -> bid.getBuyer().getName().equals(buyerName)).findFirst().orElse(null);
    }
}
