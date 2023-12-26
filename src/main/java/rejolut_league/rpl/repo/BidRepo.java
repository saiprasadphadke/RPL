package rejolut_league.rpl.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import rejolut_league.rpl.model.Bid;

public interface BidRepo extends JpaRepository<Bid, Integer> {
    
}
