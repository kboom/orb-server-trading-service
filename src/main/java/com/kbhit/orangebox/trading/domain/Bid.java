package com.kbhit.orangebox.trading.domain;

import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableSet;

@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @SequenceGenerator(name = "bid_seq", initialValue = 10000, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
    @Column(name = "bid_id")
    private Long id;

    @Column
    private DateTime placeDate;

    @ManyToOne
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @ManyToOne
    @JoinColumn(name = "bidder_id")
    private Bidder bidder;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "OFFERED_ITEMS",
            joinColumns = @JoinColumn(name = "bid_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> offeredItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "REQUESTED_ITEMS",
            joinColumns = @JoinColumn(name = "bid_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> requestedItems;


    public DateTime getPlaceDate() {
        return placeDate;
    }

    public Trade getTrade() {
        return trade;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public Set<Item> getOfferedItems() {
        return unmodifiableSet(offeredItems);
    }

    public Set<Item> getRequestedItems() {
        return unmodifiableSet(requestedItems);
    }

    @SuppressWarnings("unused")
    Bid() {

    }

    public static BidBuilder buildBid() {
        return new BidBuilder();
    }

    public static class BidBuilder {

        private Bid bid;

        BidBuilder() {
            bid = new Bid();
            bid.offeredItems = newHashSet();
            bid.requestedItems = newHashSet();
        }

        public BidBuilder withPlaceDate(ReadableDateTime dateTime) {
            bid.placeDate = new DateTime(dateTime);
            return this;
        }

        public BidBuilder withBidder(Bidder bidder) {
            bid.bidder = bidder;
            return this;
        }

        public BidBuilder withRequestedItems(Collection<Item> items) {
            bid.requestedItems.addAll(items);
            return this;
        }

        public BidBuilder withOfferedItems(Collection<Item> items) {
            bid.offeredItems.addAll(items);
            return this;
        }

        public Bid build() {
            return bid;
        }

    }

}
