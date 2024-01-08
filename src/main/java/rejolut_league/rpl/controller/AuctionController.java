package rejolut_league.rpl.controller;

import rejolut_league.rpl.model.Auction;
import rejolut_league.rpl.repo.AuctionRepo;
import rejolut_league.rpl.service.AuctionService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;





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

    @GetMapping("/status")
   public ResponseEntity<Object> getAuctionByStatus(@RequestParam String status) {
       return auctionService.findAuctionByStatus(status);
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

    @PostMapping("/close")
    public Auction closeAuction(@RequestBody AuctionService.CloseAuctionRequest entity) {
        System.out.println("Close Auction Request Received");
        return auctionService.closeAuction(entity);
    }
    
}



