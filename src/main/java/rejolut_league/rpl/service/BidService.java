package rejolut_league.rpl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import rejolut_league.rpl.model.Bid;
import rejolut_league.rpl.repo.BidRepo;

@Service
public class BidService {

    @Autowired
    BidRepo bidRepo;

    // Create
    public Bid createBid(Bid bid) {
        return bidRepo.save(bid);
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
