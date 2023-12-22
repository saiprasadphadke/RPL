package rejolut_league.rpl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;
import rejolut_league.rpl.model.User;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM public.user WHERE user_id = :id", nativeQuery = true)
    Optional<User> getUserById(Integer id);


}