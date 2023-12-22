package rejolut_league.rpl.model;

import jakarta.persistence.*;
 
import lombok.Data;


@Entity
@Data
@Table(name = "Bid", schema = "public")    
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_id")
    private Integer bidId;

    @ManyToOne
    @JoinColumn(name = "auction_id")
    private Auction auction_id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team_id;

    @Column(name = "bid_amount")
    private Double bidAmount;

    @Column(name = "player_id")
    private Integer playerId;
    
}
