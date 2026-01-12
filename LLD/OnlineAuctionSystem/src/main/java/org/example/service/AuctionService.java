package org.example.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.example.exception.NoSuchSellerExistsException;
import org.example.model.Auction;
import org.example.model.Bid;
import org.example.model.Seller;
import org.example.model.Value;
import org.example.repository.IAuctionRepository;

@AllArgsConstructor
public class AuctionService {
    private final IAuctionRepository auctionRepository;
    private final UserService userService;
    private final BidService bidService;

    public Auction createAuction(@NonNull final String auctionName, @NonNull final String sellerName, @NonNull final Value highestBid, @NonNull final Value lowestBid) {
        Seller seller = this.userService.getSeller(sellerName);
        if (seller == null) {
            throw new NoSuchSellerExistsException();
        }
        return this.auctionRepository.CreateAuction(auctionName, seller, highestBid, lowestBid);
    }

    public Bid closeAuction(@NonNull final String auctionName) {
        Auction auction = this.auctionRepository.getAuction(auctionName);
        this.auctionRepository.closeAuction(auctionName);
        return this.bidService.getWinningBid(auction);
    }

    public Auction getAuction(@NonNull final String auctionName) {
        return this.auctionRepository.getAuction(auctionName);
    }
}
