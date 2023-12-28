
package rejolut_league.rpl.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    @Column(name = "auction_id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "palayer_id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    private Player player;

    @OneToMany
    private List<Bid> bids;

    private String status;

    private Double bidAmount;
}
