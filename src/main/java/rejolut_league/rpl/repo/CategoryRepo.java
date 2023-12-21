package rejolut_league.rpl.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import rejolut_league.rpl.model.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category WHERE id = :id")
    Object getCategoryById(Integer id);
    
}
