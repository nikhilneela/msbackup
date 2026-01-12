package org.example;

import org.example.controller.AuctionController;
import org.example.controller.BidController;
import org.example.controller.UserController;
import org.example.exception.BidCannotBePlaced;
import org.example.model.Bid;
import org.example.model.Value;
import org.example.repository.*;
import org.example.service.AuctionService;
import org.example.service.BidService;
import org.example.service.UserService;
import org.example.strategy.HighestUniqueAuctionWinnerStrategy;

public class Main {
    private static IUserRepository userRepository;
    private static IAuctionRepository auctionRepository;
    private static IBidRepository bidRepository;
    private static UserService userService;
    private static BidService bidService;
    private static UserController userController;
    private static AuctionController auctionController;
    private static BidController bidController;
    private static AuctionService auctionService;

    private static void init() {
        userRepository = new InMemoryUserRepository();
        userService = new UserService(userRepository);
        userController = new UserController(userService);
        bidRepository = new InMemoryBidRepository();
        bidService = new BidService(bidRepository, new HighestUniqueAuctionWinnerStrategy());
        auctionRepository = new InMemoryAuctionRepository();
        auctionService = new AuctionService(auctionRepository, userService, bidService);
        auctionController = new AuctionController(auctionService, userService);
        bidController = new BidController(bidService, userService, auctionService);
    }


    public static void main(String[] args) {
        init();
//        testCase1();
//        testCase2();
        testCase3();
    }

    private static void testCase1() {
        userController.addBuyer("buyer1");
        userController.addBuyer("buyer2");
        userController.addBuyer("buyer3");
        userController.addSeller("seller1");
        auctionController.createAuction("A1", v(10), v(50), "seller1");
        bidController.createBid("buyer1", "A1", v(17));
        bidController.createBid("buyer2", "A1", v(15));
        Bid bid = bidController.getBid("buyer2", "A1");
        bidController.updateBid(bid.getId(), v(19));
        bidController.createBid("buyer3", "A1", v(19));

        Bid winningBid = auctionController.closeAuction("A1");

        if (winningBid != null) {
            System.out.println(String.format("Buyer %s has won the Auction %s at bidValue = %s",
                    winningBid.getBuyer().getName(),
                    winningBid.getAuction().getName(),
                    winningBid.getValue()
            ));
        }
    }

    private static void testCase2() {
        userController.addSeller("seller2");
        auctionController.createAuction("A2", v(5), v(20), "seller2");
        userController.addBuyer("buyer3");
        bidController.createBid("buyer3", "A2", v(25));
    }

    private static void testCase3() {
        userController.addBuyer("buyer1", v(20));
        userController.addBuyer("buyer2", v(20));
        userController.addBuyer("buyer3", v(20));
        userController.addSeller("seller1");
        userController.addSeller("seller2");
        auctionController.createAuction("A1", v(10), v(50), "seller1");
        auctionController.createAuction("A2", v(5), v(20), "seller2");
        bidController.createBid("buyer1", "A1", v(17));
        bidController.createBid("buyer2", "A1", v(15));
        Bid bid = bidController.getBid("buyer2", "A1");
        bidController.updateBid(bid.getId(), v(19));
        bidController.createBid("buyer3", "A1", v(19));
        Bid winningBid = auctionController.closeAuction("A1");

        if (winningBid != null) {
            System.out.println(String.format("Buyer %s has won the Auction %s at bidValue = %s",
                    winningBid.getBuyer().getName(),
                    winningBid.getAuction().getName(),
                    winningBid.getValue()
            ));
        }

        try {
            bidController.createBid("buyer1", "A2", v(5));
        } catch (BidCannotBePlaced ex) {
            System.out.println(ex.toString());
        }

        try {
            bidController.createBid("buyer3", "A2", v(25));
        } catch (BidCannotBePlaced ex) {
            System.out.println(ex.toString());
        }

        bidController.createBid("buyer2", "A2", v(5));
        bid = bidController.getBid("buyer2", "A2");
        bidController.withdrawBid(bid.getId());
        winningBid = auctionController.closeAuction("A2");
        if (winningBid != null) {
            System.out.println(String.format("Buyer %s has won the Auction %s at bidValue = %s",
                    winningBid.getBuyer().getName(),
                    winningBid.getAuction().getName(),
                    winningBid.getValue()
            ));
        } else {
            System.out.println("No Winner");
        }
    }

    private static Value v(int m) {
        return new Value(m);
    }
}