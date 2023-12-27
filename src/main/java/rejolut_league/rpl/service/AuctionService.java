package rejolut_league.rpl.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.*;
import rejolut_league.rpl.repo.*;

import java.util.List;
import java.util.Optional;


@Service
public class AuctionService {

    @Autowired
    AuctionRepo repo;

    @Autowired
    PlayerRepo userRepo;

    @Autowired
    BidRepo bidRepo;

    @Autowired
    TeamRepo teamRepo;

    // Create
    public Auction createAuction(Auction auction) {
        return repo.save(auction);
    }

    // Read
    public Auction getAuctionById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id:" + id));
    }

    public List<Auction> getAllAuctions() {
        return repo.findAll();
    }

    // Update
    public Auction updateAuction(Integer id, Auction auctionDetails) {
        Auction auction = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + id));

        auction.setBids(auctionDetails.getBids());
        // update other fields

        return repo.save(auction);
    }

    // Delete
    public void deleteAuction(Integer id) {
        Auction auction = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Auction not found with id: " + id));
        repo.delete(auction);
    }

    

    // public static class startAuction {
    //     public Integer player_id;
    // }

    // public static class bidBody {
    //     public Double bid_amount;
    //     public Integer auction_id;
    //     public Integer team_id;
    // }

    // public static class getAllAuctionsResponse {
    //     public List<Auction> auctions;
    // }

    // public static class getAuctionsBody {
    //     public Player player_id;
    //     public Double bid_amount;
    //     public String status;
    //     public List<Bid> bids;
    // }

    // public Auction startAuction(startAuction body) {

    //     Auction auction = new Auction();
    //     Player playerData = userRepo.getUserById(body.player_id);
    //     if (playerData != null) {
    //         auction.setPlayer_id((Player) playerData);
    //     } else {
    //         throw new RuntimeException("Player not found");
    //     }
    //     auction.setStatus("active");
    //     auction.setBid_amount(playerData.getCategory().getBase_price());
    //     System.out.println("Before Saving the Auction ::: ");
    //     System.out.println(auction);

    //     Auction newAuction = repo.save(auction);

    //     return newAuction;
    // }

    // public getAllAuctionsResponse getAllAuctions() {
    //     List<Auction> auctions = repo.findAll();
    //     getAllAuctionsResponse response = new getAllAuctionsResponse();

    //     response.auctions = auctions;

    //     return response;
    // }

    // public Auction placeBid(bidBody body) {

    //     // Get auction by id
    //     Auction auction = repo.findByAuctionId(body.auction_id);
    //     if (auction == null) {
    //         throw new RuntimeException("Auction not found");
    //     }

    //     // Get Team by id
    //     Optional<Team> team = teamRepo.getTeamById(body.team_id);
    //     if (team.isEmpty()) {
    //         throw new RuntimeException("Team not found");
    //     }

    //     // // Save Bid
    //     // Bid bid = new Bid();
    //     // bid.setBidAmount(body.bid_amount);
    //     // bid.setAuction_id(auction);
    //     // bid.setTeam_id(team.get());
    //     // Bid placedBid = bidRepo.save(bid);

    //     // // Set the Bid in Auction
    //     // auction.getBids().add(placedBid);
    //     Auction response = repo.save(auction);
    //     // Return the Auction
    //     return response;
    // }

    // public Auction placeBid(Integer auctionId, Auction.Bid bid) {

    // Auction auction = repo.findById(auctionId).orElseThrow(() -> new
    // RuntimeException("Auction not found"));
    // auction.getBids().add(bid);
    // return repo.save(auction);
    // }

    // public Auction getAuction(Integer auctionId) {
    // return repo.findById(auctionId).orElseThrow(() -> new
    // RuntimeException("Auction not found"));
    // }

}


