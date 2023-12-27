package rejolut_league.rpl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rejolut_league.rpl.model.Auction;

public interface AuctionRepo extends JpaRepository<Auction, Integer> {

    @Query(value = "SELECT * FROM public.auction WHERE auction_id = ?1", nativeQuery = true)
    Auction findByAuctionId(Integer auctionId);

}
