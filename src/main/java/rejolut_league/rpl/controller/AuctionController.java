package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Auction;
import rejolut_league.rpl.model.Team;
import rejolut_league.rpl.repo.AuctionRepo;
import rejolut_league.rpl.service.AuctionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    AuctionRepo repo;

    @Autowired
    AuctionService auctionService;

    @PostMapping("/")
    public Auction createAuction(@RequestBody AuctionService.startAuction entity) {
        //TODO: process POST request
        Auction response = auctionService.startAuction(entity);
        System.out.println("After response in controller");
        System.out.println(response);
        
        return response;
    }

    @GetMapping("/all")
    public List<Auction> getMethodName() {
       
        List<Auction> response = repo.findAll();
        return response;
    }
    
    @GetMapping("/{id}")
    public Optional<Auction> getAuction(@PathVariable Integer id) {

        Optional<Auction> response = repo.findById(id);

        return response;

    }
    
    @PostMapping("/bid")
    public ResponseEntity<Object> placeBid(@RequestBody AuctionService.bidBody entity) {
        
        Auction response = auctionService.placeBid(entity);
        
        return ResponseEntity.ok().body(response);
    }



}
