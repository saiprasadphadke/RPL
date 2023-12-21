package rejolut_league.rpl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rejolut_league.rpl.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
    

}