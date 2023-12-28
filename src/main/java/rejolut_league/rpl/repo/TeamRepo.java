package rejolut_league.rpl.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rejolut_league.rpl.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rejolut_league.rpl.model.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {
    
    @Query(value = "SELECT * FROM public.team WHERE team_login_id = ?1", nativeQuery = true)
    Team getTeamByLoginId(String loginId);

    @Query(value = "SELECT * FROM public.team WHERE team_id = ?1", nativeQuery = true)
    Optional<Team> getTeamById(Integer teamId);

}