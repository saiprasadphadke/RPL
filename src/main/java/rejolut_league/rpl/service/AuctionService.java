package rejolut_league.rpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.*;
import rejolut_league.rpl.repo.*;

import java.util.List;


@Service
public class AuctionService {

    @Autowired
    AuctionRepo repo;

    @Autowired
    PlayerRepo playerRepo;

    @Autowired
    TeamRepo teamRepo;

    // Create
    public Auction createAuction(startAuction body) {

        Auction auction = new Auction();

        Player playerData = playerRepo.findById(body.playerId)
            .orElseThrow(() -> new RuntimeException("Player not found"));

        auction.setPlayer(playerData);
        auction.setStatus("active");
        auction.setBidAmount(playerData.getCategory().getBasePrice());

        System.out.println("Before Saving the Auction ::: ");

        Auction newAuction = repo.save(auction);

        return newAuction;
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

    public Auction closeAuction(CloseAuctionRequest entity) {

        Auction auction = repo.findById(entity.auctionId)
                .orElseThrow(() -> new RuntimeException("Auction not found"));

            auction.setStatus("closed");
            // get the latest bid
            Bid bid = auction.getBids().get(auction.getBids().size() - 1);
            
            Player player = auction.getPlayer();
            player.setTeam(bid.getTeam());
            playerRepo.save(player);
            
            Team team = bid.getTeam();
            team.getPlayers().add(player);
            teamRepo.save(team);

            repo.save(auction);
            
        return auction;
    }

    

    public static class startAuction {
        public Integer playerId;
    }

    public static class CloseAuctionRequest {
        public Integer auctionId;
    }

}


