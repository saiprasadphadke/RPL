package rejolut_league.rpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.*;
import rejolut_league.rpl.repo.*;

import java.util.Optional;


@Service
public class AuctionService {

    @Autowired
    AuctionRepo repo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    BidRepo bidRepo;

    @Autowired
    TeamRepo teamRepo;
    
    public static class startAuction {
        public Integer player_id;
    }

    public static class bidBody {
        public Double bid_amount;
        public Integer auction_id;
        public Integer team_id;
    }

    public Auction startAuction(startAuction body) {

        Auction auction = new Auction();
        User playerData = userRepo.getUserById(body.player_id);
        if(playerData!= null) {
            auction.setPlayer_id((User) playerData);
        } else {
            throw new RuntimeException("Player not found");
        }
        auction.setStatus("active");
        auction.setBid_amount(playerData.getCategory().getBase_price());
        System.out.println("Before Saving the Auction ::: ");
        System.out.println(auction);

        Auction newAuction = repo.save(auction);

        return newAuction;
    }   

    public Auction placeBid(bidBody body) {

        // Get auction by id
        Auction auction = repo.findByAuctionId(body.auction_id);
        if(auction == null) {
            throw new RuntimeException("Auction not found");
        }

        // Get Team by id
        Optional<Team> team = teamRepo.getTeamById(body.team_id);
        if(team.isEmpty()) {
            throw new RuntimeException("Team not found");
        }

        // //  Save Bid
        // Bid bid = new Bid();
        // bid.setBidAmount(body.bid_amount);
        // bid.setAuction_id(auction);
        // bid.setTeam_id(team.get());
        // Bid placedBid = bidRepo.save(bid);

        // // Set the Bid in Auction
        // auction.getBids().add(placedBid);
        Auction response = repo.save(auction);
        // Return the Auction
        return response;
    }

    // public Auction placeBid(Integer auctionId, Auction.Bid bid) {

    //     Auction auction = repo.findById(auctionId).orElseThrow(() -> new RuntimeException("Auction not found"));
    //     auction.getBids().add(bid);
    //     return repo.save(auction);
    // }

    // public Auction getAuction(Integer auctionId) {
    //     return repo.findById(auctionId).orElseThrow(() -> new RuntimeException("Auction not found"));
    // }


}
