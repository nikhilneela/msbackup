package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Buyer;
import org.example.model.Value;
import org.example.service.AuctionService;
import org.example.service.BidService;
import org.example.service.UserService;

@AllArgsConstructor
public class BidController {
    private BidService bidService;
    private UserService userService;
    private AuctionService auctionService;

    public Bid createBid(@NonNull final String buyerName, @NonNull final String auctionName, @NonNull final Value bidValue) {
        Buyer buyer = this.userService.getBuyer(buyerName);
        if (buyer == null) {
            throw new RuntimeException(String.format("Buyer %s does not exist", buyerName));
        }
        Auction auction = this.auctionService.getAuction(auctionName);
        return this.bidService.placeBid(buyer, auction, bidValue);
    }

    public boolean updateBid(@NonNull final String bidId, @NonNull final Value value) {
        return this.bidService.updateBid(bidId, value);
    }

    public void withdrawBid(@NonNull final String bidId) {
        this.bidService.withdrawBid(bidId);
    }

    public Bid getBid(@NonNull final String buyerName, @NonNull final String auctionName) {
        return this.bidService.getBid(buyerName, auctionService.getAuction(auctionName));
    }
}
