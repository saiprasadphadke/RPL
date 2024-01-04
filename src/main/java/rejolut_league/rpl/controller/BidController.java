package rejolut_league.rpl.controller;

import jakarta.servlet.http.HttpServletRequest;
import rejolut_league.rpl.model.Bid;
import rejolut_league.rpl.repo.BidRepo;
import rejolut_league.rpl.service.BidService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;




@RestController
@CrossOrigin(origins = "https://f6ad-2405-201-1f-1c98-73-4307-95bf-2ffd.ngrok-free.app", allowedHeaders = "*")
@RequestMapping("/bid")
public class BidController {

    @Autowired
    BidRepo bidRepo;

    @Autowired
    BidService bidService;

    @PostMapping("/submit")
    public Bid placeBid(HttpServletRequest httpRequest, @RequestBody BidService.createBidRequest entity) {
        // fetch attribute set in httpRequest
        Integer teamId = Integer.parseInt(httpRequest.getAttribute("teamId").toString());
    
        Bid response = bidService.createBid(teamId, entity);
        return response;
    }

    @GetMapping("/all")
    public List<Bid> getAllBids() {
        return bidRepo.findAll();
    }

}
