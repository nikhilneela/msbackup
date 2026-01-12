package org.example.controller;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Value;
import org.example.service.AuctionService;
import org.example.service.UserService;

@AllArgsConstructor
public class AuctionController {
    private AuctionService auctionService;
    private UserService userService;

    public Auction createAuction(@NonNull final String auctionName, @NonNull final Value lowestValue, @NonNull final Value highestValue, @NonNull final String sellerName) {
        return this.auctionService.createAuction(auctionName, sellerName, highestValue, lowestValue);
    }

    public Bid closeAuction(@NonNull final String auctionName) {
        Bid bid = this.auctionService.closeAuction(auctionName);
        if (bid != null) {
            this.userService.updateBudget(bid.getBuyer().getName(), bid.getValue());
        }
        return bid;
    }
}
