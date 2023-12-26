package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Bid;
import rejolut_league.rpl.repo.BidRepo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    BidRepo bidRepo;

    @PostMapping("/")
    public Bid placeBid(@RequestBody Bid entity) {
        //TODO: process POST request
        
        
        return entity;
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidRepo.findAll();
    }


}
