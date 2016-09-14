package com.kbhit.orangebox.trading.domain;

import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import javax.persistence.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.Collections.unmodifiableList;

@Entity
@Table(name = "TRADES")
public class Trade {

    @EmbeddedId
    private TradeId id;

    private DateTime createDate;

    private DateTime updateDate;

    public Trade(TradeId id) {
        this.id = id;
        this.historicBids = newArrayList();
    }

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

    public TradeId getId() {
        return id;
    }

    public Bid getInitialBid() {
        return initialBid;
    }

    public Bid getLatestBid() {
        return latestBid;
    }

    public List<Bid> getHistoricBids() {
        return unmodifiableList(historicBids);
    }

    public boolean isActive() {
        return true;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public Bidder getRequester() {
        return requester;
    }

    public Bidder getResponder() {
        return responder;
    }

    public static class TradeBuilder {

        private Trade trade;

        private TradeBuilder(TradeId tradeId) {
            trade = new Trade(tradeId);
        }

        public TradeBuilder withInitialBid(Bid initialBid) {
            trade.initialBid = initialBid;
            trade.latestBid = initialBid;
            trade.historicBids.add(initialBid);
            return this;
        }

        public TradeBuilder createdOn(ReadableDateTime createDate) {
            trade.createDate = new DateTime(createDate);
            trade.updateDate = new DateTime(createDate);
            return this;
        }

        public TradeBuilder withRequester(Bidder requester) {
            trade.requester = requester;
            return this;
        }

        public TradeBuilder withResponder(Bidder responder) {
            trade.responder = responder;
            return this;
        }

        public Trade build() {
            return trade;
        }

    }

    public static TradeBuilder aTrade(TradeId tradeId) {
        return new TradeBuilder(tradeId);
    }

    @SuppressWarnings("unused")
    Trade() {

    }

}
