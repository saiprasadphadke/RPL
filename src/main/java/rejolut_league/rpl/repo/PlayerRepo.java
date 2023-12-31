package rejolut_league.rpl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import rejolut_league.rpl.model.Player;
import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player, Integer> {

    @Query(value = "SELECT * FROM public.player WHERE id = ?1", nativeQuery = true)
    Player getPlayerById(Integer id);

}