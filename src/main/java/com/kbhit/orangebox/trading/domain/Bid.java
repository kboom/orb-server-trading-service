package com.kbhit.orangebox.trading.domain;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @SequenceGenerator(name = "bid_seq", initialValue = 10000, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
    private Long id;

    @Column
    @Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime placeDate;

    @ManyToOne
    private Trade trade;

    @ManyToOne
    private Bidder bidder;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private Set<Item> offeredItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private Set<Item> requestedItems;

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
            bid.offeredItems.addAll(items);
            return this;
        }

        public BidBuilder withOfferedItems(Collection<Item> items) {
            bid.requestedItems.addAll(items);
            return this;
        }

        public Bid build() {
            return bid;
        }

    }

}
