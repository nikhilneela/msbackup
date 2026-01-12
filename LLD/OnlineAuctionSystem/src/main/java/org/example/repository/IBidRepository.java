package org.example.repository;

import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Buyer;
import org.example.model.Value;

public interface IBidRepository {
    Bid CreateBid(Buyer buyer, Auction auction, Value value);
    Bid getBid(String id);
}
