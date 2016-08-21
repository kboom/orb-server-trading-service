package com.kbhit.orangebox.trading.domain;

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

    private Date placeDate;

    @ManyToOne
    private Bidder bidder;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private Set<Item> offeredItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "id")
    private Set<Item> requestedItems;

    Bid() {}

    public static BidBuilder buildBid() {
        return new BidBuilder();
    }

    public static class BidBuilder {

        private Bid bid;

        BidBuilder() {
            bid = new Bid();
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
