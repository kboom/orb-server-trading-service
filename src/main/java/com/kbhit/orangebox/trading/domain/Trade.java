package com.kbhit.orangebox.trading.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TRADES")
public class Trade {

    @EmbeddedId
    private TradeId id;

    private Date createDate;

    private Date updateDate;

    @OneToOne
    @JoinColumn(name = "requester_id")
    private Bidder requester;

    @OneToOne
    @JoinColumn(name = "responder_id")
    private Bidder responder;

    @OneToOne
    @JoinColumn(name = "latest_bid_id")
    private Bid latestBid;

    @OneToOne
    @JoinColumn(name = "initial_bid_id")
    private Bid initialBid;

    @OneToMany(mappedBy = "trade")
    private List<Bid> historicBids;

    public Bid getInitialBid() {
        return initialBid;
    }

    public Bid getLatestBid() {
        return latestBid;
    }

    public List<Bid> getHistoricBids() {
        return Collections.unmodifiableList(historicBids);
    }

    public boolean isActive() {
        return true;
    }

}
