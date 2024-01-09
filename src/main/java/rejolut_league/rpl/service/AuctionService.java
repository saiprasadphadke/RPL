package rejolut_league.rpl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.*;
import rejolut_league.rpl.repo.*;

import java.util.*;


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
                .orElseThrow(() -> new RuntimeException("Auction not found with id:" + id));
    }

    public List<Auction> getAllAuctions() {
        return repo.findAll();
    }

    // Update
    public Auction updateAuction(Integer id, Auction auctionDetails) {
        Auction auction = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + id));

        auction.setBids(auctionDetails.getBids());
        // update other fields

        return repo.save(auction);
    }

    // Delete
    public void deleteAuction(Integer id) {
        Auction auction = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + id));
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
        Double balance = team.getBalance();
        team.setBalance(balance - bid.getAmount());
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

    public static class AuctionResponseObject {
        public Integer id;
        public String status;
        public Double bidAmount;
        public Integer playerId;
        public String playerName;
        public Integer teamId;
        public String teamName;
        public String playerStatus;
        
        public void setId(int id) {
            this.id = id;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setBidAmount(Double bidAmount) {
            this.bidAmount = bidAmount;
        }

        public void setPlayerId(int playerId) {
            this.playerId = playerId;
        }

        public void setPlayerName(String playerName) {
            this.playerName = playerName;
        }

        public void setTeamId(int teamId) {
            this.teamId = teamId;
        }

        public void setTeamName(String teamName) {
            this.teamName = teamName;
        }

        public void setPlayerStatus(String playerStatus) {
            this.playerStatus = playerStatus;
        }
    }

    public ResponseEntity<Object> findAuctionByStatus(String status) {
        List<Auction> auctionList = repo.findAuctionByStatus(status);
        if (auctionList.isEmpty()) {
            return ResponseEntity.ok().body(new ArrayList<>());
        } 
        if (status.equals("active")) {
            List<Object> customAuctionList = new ArrayList<>();
            for (int i = 0; i < auctionList.size(); i++) {
                Auction auction = auctionList.get(i);
                List<Bid> bidList = auction.getBids();
                AuctionResponseObject customAuction = new AuctionResponseObject();
                if (bidList.isEmpty()) {
                    // Create custom object with desired fields
                    customAuction.setBidAmount(auction.getBidAmount());
                    customAuction.setPlayerStatus("No Bids Yet");
                }
                else {
                    Bid bid = bidList.get(bidList.size() - 1);
                    customAuction.setBidAmount(bid.getAmount());
                    customAuction.setTeamId(bid.getTeam().getId());
                    customAuction.setTeamName(bid.getTeam().getName());
                    customAuction.setPlayerStatus("Ongoing");
                    
                }
                customAuction.setId(auction.getId());
                customAuction.setStatus(auction.getStatus());
                customAuction.setPlayerId(auction.getPlayer().getId());
                customAuction.setPlayerName(auction.getPlayer().getName());
                customAuctionList.add(customAuction);

            }
            
            return ResponseEntity.ok().body(customAuctionList);
        }
        else if (status.equals("closed")){
            List<Object> customAuctionList = new ArrayList<>();
            for (int i = 0; i < auctionList.size(); i++) {
                Auction auction = auctionList.get(i);
                List<Bid> bidList = auction.getBids();
                AuctionResponseObject customAuction = new AuctionResponseObject();
                if (bidList.isEmpty()) {
                    // Create custom object with desired fields
                    customAuction.setBidAmount(auction.getBidAmount());
                    customAuction.setPlayerStatus("Unsold");
                }
                else {
                    Bid bid = bidList.get(bidList.size() - 1);
                    customAuction.setBidAmount(bid.getAmount());
                    customAuction.setTeamId(bid.getTeam().getId());
                    customAuction.setTeamName(bid.getTeam().getName());
                    customAuction.setPlayerStatus("Sold");
                    
                }
                customAuction.setId(auction.getId());
                customAuction.setStatus(auction.getStatus());
                customAuction.setPlayerId(auction.getPlayer().getId());
                customAuction.setPlayerName(auction.getPlayer().getName());
                customAuctionList.add(customAuction);

            }
            
            return ResponseEntity.ok().body(customAuctionList);
        }
        else {
            return ResponseEntity.badRequest().body("Invalid status: " + status);
        }
    }

    public List<Map<String, Object>> getMostExpensivePlayer() {

        List<Map<String, Object>> mostExpensivePlayer = new ArrayList<>();
        List<Auction> auctionList = repo.findAuctionByStatus("closed");
        if (auctionList.isEmpty()) {
            return mostExpensivePlayer;
        }
        for (int i = 0; i < auctionList.size(); i++) {
            Auction auction = auctionList.get(i);
            List<Bid> bidList = auction.getBids();
            if (bidList.isEmpty()) {
                continue;
            }
            Bid bid = bidList.get(bidList.size() - 1);
            Map<String, Object> player = new HashMap<>();
            player.put("playerName", auction.getPlayer().getName());
            player.put("bidAmount", bid.getAmount());
            player.put("teamName", bid.getTeam().getName());
            mostExpensivePlayer.add(player);
        }
        Collections.sort(mostExpensivePlayer, new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                return ((Double) o2.get("bidAmount")).compareTo((Double) o1.get("bidAmount"));
            }
        });
        return mostExpensivePlayer;
        
    }

}



