package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.service.Item;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toList;

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
    private Set<BidItem> offeredItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "REQUESTED_ITEMS",
            joinColumns = @JoinColumn(name = "bid_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<BidItem> requestedItems;


    public DateTime getPlaceDate() {
        return placeDate;
    }

    public Trade getTrade() {
        return trade;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public Set<BidItem> getOfferedItems() {
        return unmodifiableSet(offeredItems);
    }

    public Set<BidItem> getRequestedItems() {
        return unmodifiableSet(requestedItems);
    }

    @SuppressWarnings("unused")
    Bid() {

    }

    public static BidBuilder buildBid(BidderService bidderService) {
        return new BidBuilder(bidderService);
    }

    public static class BidBuilder {

        private Bid bid;
        private BidderService bidderService;

        BidBuilder(BidderService bidderService) {
            this.bidderService = bidderService;
            this.bid = new Bid();
            this.bid.offeredItems = newHashSet();
            this.bid.requestedItems = newHashSet();
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
            Bidder owningBidder = bidderService.getOwnerOf(items);
            bid.requestedItems.addAll(items.stream().map(item -> createBidItem(item, owningBidder)).collect(toList()));
            return this;
        }

        public BidBuilder withOfferedItems(Collection<Item> items) {
            Bidder owningBidder = bidderService.getOwnerOf(items);
            bid.offeredItems.addAll(items.stream().map(item -> createBidItem(item, owningBidder)).collect(toList()));
            return this;
        }

        private BidItem createBidItem(Item item, Bidder bidder) {
            return new BidItem(item, bidder, bid);
        }

        public Bid build() {
            return bid;
        }

    }

}
