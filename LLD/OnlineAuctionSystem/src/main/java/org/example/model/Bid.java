package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Bid {
    private String id;
    private Buyer buyer;
    private Auction auction;
    private Value value;
    @Setter
    private BidStatus status;
    public boolean updateValue(Value value) {
        if (status != BidStatus.ACTIVE) {
            return false;
        }

        if (!value.isBetweenInclusive(auction.getMinValue(), auction.getMaxValue())) {
            return false;
        }
        this.value = value;
        return true;
    }
}
