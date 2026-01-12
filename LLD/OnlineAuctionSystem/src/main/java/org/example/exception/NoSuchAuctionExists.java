package org.example.exception;

public class NoSuchAuctionExists extends RuntimeException {
    private final String auctionName;
    public NoSuchAuctionExists(String auctionName) {
        this.auctionName = auctionName;
    }

    @Override
    public String toString() {
        return String.format("An Auction for product %s does not exist", auctionName);
    }
}
