package org.example.strategy;

import lombok.NonNull;
import org.example.model.Bid;

import java.util.List;

public interface IAuctionWinnerDecisionStrategy {
    Bid getWinnigBid(@NonNull final List<Bid> bids);
}
