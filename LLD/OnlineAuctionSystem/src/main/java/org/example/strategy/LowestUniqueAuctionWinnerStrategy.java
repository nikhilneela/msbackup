package org.example.strategy;

import lombok.NonNull;
import org.example.model.Bid;
import org.example.model.BidStatus;
import org.example.model.Value;

import java.util.*;

public class LowestUniqueAuctionWinnerStrategy implements IAuctionWinnerDecisionStrategy {
    @Override
    public Bid getWinnigBid(@NonNull final List<Bid> bids) {

        Map<Value, List<Bid>> valueToBidsMap = new TreeMap<>((a, b) -> a.equals(b) ? 0 : (a.isLesserThanOrEqual(b) ? -1 : 1));

        List<Bid> activeBids = bids.stream().filter(bid -> bid.getStatus() == BidStatus.ACTIVE).toList();

        activeBids.forEach(bid -> {
            valueToBidsMap.computeIfAbsent(bid.getValue(), k -> new ArrayList<>()).add(bid);
        });

        return valueToBidsMap.values().stream()
                .filter(bidlist -> bidlist.size() == 1)
                .map(bidlist -> bidlist.get(0))
                .findFirst()
                .orElse(null);
    }
}
