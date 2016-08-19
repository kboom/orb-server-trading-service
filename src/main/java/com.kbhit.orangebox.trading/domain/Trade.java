package com.kbhit.orangebox.trading.domain;

import javax.persistence.*;
import java.sql.Date;

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



}
