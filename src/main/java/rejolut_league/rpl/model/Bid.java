package rejolut_league.rpl.model;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "Bid", schema = "public")
public class Bid {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private Integer bid_id;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "auction_id")
    // private Auction auction;

    // @ManyToOne
    // @JoinColumn(name = "team_id")
    // private Team team;

    // @Column(name = "bid_amount")
    // private Double bidAmount;
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Auction auction;

    @ManyToOne
    private Team team;

    @Column
    private double amount;

}
