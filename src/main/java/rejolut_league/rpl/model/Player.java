package rejolut_league.rpl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Player", schema = "public")
public class Player {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "player_id")
    // private int id;

    // private String user_name;

    // private String email;

    // private int age;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "category_id")
    // private Category category;

    // @ManyToOne
    // @JoinColumn(name = "team_id")
    // private Team team;

    // @OneToOne(mappedBy = "player_id")
    // @JoinColumn(name = "auction_id")
    // private Auction auction;

    // private String user_image_url;

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @ManyToOne
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "player")
    @JoinColumn(name = "id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Auction auction;


}
