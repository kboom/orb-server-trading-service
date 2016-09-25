package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.service.Item;
import org.joda.time.DateTime;
import org.joda.time.ReadableDateTime;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;
import static com.kbhit.orangebox.trading.domain.TradedItem.traded;
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
    private Set<TradedItem> offeredItems;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "REQUESTED_ITEMS",
            joinColumns = @JoinColumn(name = "bid_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<TradedItem> requestedItems;


    public DateTime getPlaceDate() {
        return placeDate;
    }

    public Trade getTrade() {
        return trade;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public Set<TradedItem> getOfferedItems() {
        return unmodifiableSet(offeredItems);
    }

    public Set<TradedItem> getRequestedItems() {
        return unmodifiableSet(requestedItems);
    }

    @SuppressWarnings("unused")
    Bid() {

    }

    public static BidBuilder buildBidFor(Trade trade) {
        return new BidBuilder(trade);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (placeDate != null ? !placeDate.equals(bid.placeDate) : bid.placeDate != null) return false;
        if (trade != null ? !trade.equals(bid.trade) : bid.trade != null) return false;
        return bidder != null ? bidder.equals(bid.bidder) : bid.bidder == null;

    }

    @Override
    public int hashCode() {
        int result = placeDate != null ? placeDate.hashCode() : 0;
        result = 31 * result + (trade != null ? trade.hashCode() : 0);
        result = 31 * result + (bidder != null ? bidder.hashCode() : 0);
        return result;
    }

    public static class BidBuilder {

        private Bid bid;
        private Trade trade;

        BidBuilder(Trade trade) {
            this.trade = trade;
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
            Bidder owningBidder = trade.getResponder();
            ensureAllItemsBelongTo(items, owningBidder);
            bid.requestedItems.addAll(items.stream().map(
                    item -> traded(item).in(trade).ownedBy(owningBidder).build()
            ).collect(toList()));
            return this;
        }

        public BidBuilder withOfferedItems(Collection<Item> items) {
            Bidder owningBidder = trade.getRequester();
            ensureAllItemsBelongTo(items, owningBidder);
            bid.offeredItems.addAll(items.stream().map(
                    item -> traded(item).in(trade).ownedBy(owningBidder).build()
            ).collect(toList()));
            return this;
        }

        public Bid build() {
            return bid;
        }

        private void ensureAllItemsBelongTo(Collection<Item> claimedItems, Bidder owningBidder) {
            // todo check if all claimedItems belong to owningBidder, throw custom unchecked exception otherwise
        }

    }

}
