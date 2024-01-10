package rejolut_league.rpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
// import org.springframework.messaging.simp.SimpMessagingTemplate;
import rejolut_league.rpl.Constants;
import rejolut_league.rpl.model.Auction;
import rejolut_league.rpl.model.Bid;
import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.AuctionRepo;
import rejolut_league.rpl.repo.BidRepo;
import rejolut_league.rpl.repo.TeamRepo;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;

    @Autowired
    AuctionRepo auctionRepo;

    @Autowired
    TeamRepo teamRepo;

    // @Autowired
    // private SimpMessagingTemplate template;

    public static class createBidRequest {
        public Integer auctionId;
    }

    // Create
    public Bid createBid(Integer teamId, createBidRequest body) {

        // try {
            Bid bid = new Bid();

            Auction auctionData = auctionRepo.findById(body.auctionId)
                    .orElseThrow(() -> new RuntimeException("Auction not found"));
            bid.setAuction(auctionData);

            if (auctionData.getBids().size() > 0) {
                Bid lastestBid = (Bid) auctionData.getBids().get(auctionData.getBids().size() - 1);
                if (lastestBid.getTeam().getId() == teamId) {
                    throw new RuntimeException("Cannot bid on your own bid");
                }
                Integer bidSize = auctionData.getBids().size();
                Double bidStepSize = Constants.BID_STEP_SIZE_0;
                if (bidSize >= 40 ) {
                    bidStepSize = Constants.BID_STEP_SIZE_40;
                }
                else  if (bidSize >= 25) {
                    bidStepSize = Constants.BID_STEP_SIZE_25;
                }
                else if (bidSize >= 10) {
                    bidStepSize = Constants.BID_STEP_SIZE_10;
                }
                Double newBidAmount = auctionData.getBidAmount() + bidStepSize;
                bid.setAmount(newBidAmount);
                auctionData.setBidAmount(newBidAmount);
            }
            else {
                bid.setAmount(auctionData.getBidAmount());
            }

            Team teamData = teamRepo.findById(teamId)
                    .orElseThrow(() -> new RuntimeException("Team not found"));
            bid.setTeam(teamData);

            Bid response = bidRepo.save(bid);

            auctionData.getBids().add(response);
            auctionRepo.save(auctionData);
            // this.template.convertAndSend("/topic/auctionUpdate", response);
            return response;

            // Map<String, Object> result = new HashMap<String,Object>();
            // result.put("bid", response);
            // return result;

        //     }
        // catch (Exception e) {
        //     HashMap<String, Object> errorMap = new HashMap<>();
        //     errorMap.put("error", e);
        //     return errorMap;
        // }
    }

    // Read
    public Bid getBidById(Integer id) {
        return bidRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id:" + id));
    }

    public List<Bid> getAllBids() {
        return bidRepo.findAll();
    }

    // Update
    public Bid updateBid(Integer id, Bid bidDetails) {
        Bid bid = bidRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));

        bid.setAmount(bidDetails.getAmount());
        // update other fields

        return bidRepo.save(bid);
    }

    // Delete
    public void deleteBid(Integer id) {
        Bid bid = bidRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with id: " + id));
        bidRepo.delete(bid);
    }

}

