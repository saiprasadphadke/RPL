package rejolut_league.rpl.controller;

import org.springframework.web.bind.annotation.RestController;

import rejolut_league.rpl.model.Auction;
import rejolut_league.rpl.repo.AuctionRepo;
import rejolut_league.rpl.service.AuctionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;




@RestController
@ResponseBody
@RequestMapping("/auction")
public class AuctionController {

    @Autowired
    AuctionRepo repo;

    @Autowired
    AuctionService auctionService;

    // Create
    @PostMapping("")
    public Auction createAuction(@RequestBody AuctionService.startAuction auction) {
        return auctionService.createAuction(auction);
    }

    // Read
    @GetMapping("/{id}")
    public Auction getAuctionById(@PathVariable Integer id) {
        return auctionService.getAuctionById(id);
    }

    @GetMapping("")
    public List<Auction> getAllAuctions() {
        return auctionService.getAllAuctions();
    }

    // Update
    @PutMapping("/{id}")
    public Auction updateAuction(@PathVariable Integer id, @RequestBody Auction auction) {
        return auctionService.updateAuction(id, auction);
    }

    // Delete
    @DeleteMapping("/{id}")
    public void deleteAuction(@PathVariable Integer id) {
        auctionService.deleteAuction(id);
    }

}



