package org.example.strategy;

import lombok.NonNull;
import org.example.model.Bid;
import org.example.model.BidStatus;
import org.example.model.Value;

import java.util.*;
import java.util.stream.Collectors;

public class HighestUniqueAuctionWinnerStrategy implements IAuctionWinnerDecisionStrategy {
    @Override
    public Bid getWinnigBid(@NonNull final List<Bid> bids) {
        Map<Value, List<Bid>> valueToBidsTreeMap = new TreeMap<>((o1, o2) ->
            o2.equals(o1) ? 0 : (o1.isGreaterThanOrEqual(o2) ? -1 : 1)
        );

        List<Bid> activeBids = bids.stream().filter(bid -> bid.getStatus() == BidStatus.ACTIVE).toList();

        activeBids.forEach(bid -> {
            valueToBidsTreeMap.computeIfAbsent(bid.getValue(), k -> new ArrayList<>()).add(bid);
        });

        return valueToBidsTreeMap.values().stream()
                .filter(l -> l.size() == 1)
                .map(bidlist -> bidlist.get(0))
                .findFirst()
                .orElse(null);
    }
}
