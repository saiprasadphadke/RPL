package rejolut_league.rpl.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
// import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Team", schema = "public")
public class Team {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "team_id")
    // public int id;

    // private String team_name;

    // private int win;

    // private int loss;

    // private int draw;  

    // private int team_total_match;

    // private String team_image_url;
    
    private String teamLoginId;
    
    private String password;

    // private String team_symbol;
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;

    @Column
    private String name;

    @Column
    private int totalMatches;

    @Column
    private int matchesWon;

    @Column
    private int matchesLost;

    @Column
    private int matchesDrawn;

    @OneToMany(mappedBy = "team")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    private List<Bid> bids;

  }
