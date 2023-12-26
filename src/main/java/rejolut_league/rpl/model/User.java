package rejolut_league.rpl.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "User", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int id;

    private String user_name;

    private String email;

    private int age;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToOne(mappedBy = "player_id")
    @JoinColumn(name = "auction_id")
    private Auction auction;

    private String user_image_url; 
    
}
