package rejolut_league.rpl.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "Auction", schema = "public")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auction_id")
    private Integer auctionId;

    @OneToOne
    @JoinColumn(name = "player_id")
    private User player_id;

    private Double bid_amount;

    @OneToMany
    @JoinColumn(name = "bid_id")
    private Bid[] bids;

}
