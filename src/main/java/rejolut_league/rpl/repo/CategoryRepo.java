package rejolut_league.rpl.repo;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import rejolut_league.rpl.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

    @Query(value = "SELECT * FROM category WHERE id = :id")
    Object getCategoryById(Integer id);

    @Query(value = "SELECT * FROM category WHERE name = ?name")
    List<Category> getBasePriceByName(String name);

    @Query(value = "SELECT * FROM category WHERE name = ?1 and base_price = :2")
    Object getByNameAndBasePrice(String name, Double basePrice);

}
