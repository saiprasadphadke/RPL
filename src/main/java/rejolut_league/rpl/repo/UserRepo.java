package rejolut_league.rpl.repo;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.transaction.annotation.Transactional;

import rejolut_league.rpl.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    // @Transactional() 
    // @Query(value = "INSERT INTO public.user values (?1, ?2, ?3) as (user_name, email, age)", nativeQuery = true)
    // Object saveUser (String userName, String email, int age, String categoryId);

}

