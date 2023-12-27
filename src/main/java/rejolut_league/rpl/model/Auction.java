package rejolut_league.rpl.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Auction", schema = "public")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Auction {

    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "auction_id")
    // private Integer auctionId;

    // @OneToOne
    // @JoinColumn(name = "player_id")
    // private User player_id;

    // private Double bid_amount;

    // // @JoinColumn(name = "bid_id")
    // @OneToMany(fetch = FetchType.LAZY, mappedBy = "auction")
    // private List<Bid> bids;

    // private String status;
    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Player player;

    @OneToMany
    private List<Bid> bids;

}
