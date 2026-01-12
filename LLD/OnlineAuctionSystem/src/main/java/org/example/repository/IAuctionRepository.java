package org.example.repository;

import org.example.model.Auction;
import org.example.model.Seller;
import org.example.model.Value;

public interface IAuctionRepository {
    Auction CreateAuction(String auctionName, Seller seller, Value highestBid, Value lowestBid);
    Auction getAuction(String auctionName);
    void closeAuction(String auctionName);
}
