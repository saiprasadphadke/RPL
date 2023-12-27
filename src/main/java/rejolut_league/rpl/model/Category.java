package rejolut_league.rpl.model;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Category", schema = "public")
public class Category {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "category_id")
    // public int id;

    // private String name;

    // private double base_price;

    // private String type;

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private int id;

    @Column
    private String name;

    @Column
    private double basePrice;

    @OneToMany(mappedBy="category")
    private List<Player> players;
    
    public Category() {

    }
}
