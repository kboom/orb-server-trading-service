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
    private Bidder requester;

    @OneToOne
    private Bidder responder;

    @ManyToOne
    private Bid latestBid;

    @OneToOne
    private Bid initialBid;

    @OneToMany
    @JoinColumn(name = "trade")
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
