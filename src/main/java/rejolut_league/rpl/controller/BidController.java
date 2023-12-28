package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Bid;
import rejolut_league.rpl.repo.BidRepo;
import rejolut_league.rpl.service.BidService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    BidRepo bidRepo;

    @Autowired
    BidService bidService;

    @PostMapping("")
    public Bid placeBid(@RequestBody BidService.createBidRequest entity) {

        Bid response = bidService.createBid(entity);
        return response;
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidRepo.findAll();
    }

}
