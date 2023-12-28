package rejolut_league.rpl.model;

import java.util.List;

import jakarta.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "Team", schema = "public")
public class Team {

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
