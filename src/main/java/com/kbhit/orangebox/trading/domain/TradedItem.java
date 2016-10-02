package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.service.Item;

import javax.persistence.*;

import static com.kbhit.orangebox.trading.domain.ItemId.itemId;
import static com.kbhit.orangebox.trading.domain.TradedItemId.tradedItemId;

@Entity
@Table(name = "TRADED_ITEMS")
public class TradedItem {

    @EmbeddedId
    private TradedItemId tradedItemId;

    @ManyToOne
    @JoinColumn(name = "trade_id", insertable = false, updatable = false)
    private Trade trade;

    @ManyToOne
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private Bidder owner;

    private String name;

    private TradedItem(TradedItemId tradedItemId) {
        this.tradedItemId = tradedItemId;
    }

    public ItemId getItemId() {
        return tradedItemId.getItemId();
    }


    public Bidder getOwner() {
        return owner;
    }
    
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    TradedItem() {

    }

    static class TradedItemBuilder {

        private final Item item;
        private Trade trade;
        private Bidder owningBidder;

        TradedItemBuilder(Item item) {
            this.item = item;
        }

        TradedItemBuilder in(Trade trade) {
            this.trade = trade;
            return this;
        }

        TradedItemBuilder ownedBy(Bidder owningBidder) {
            this.owningBidder = owningBidder;
            return this;
        }

        public TradedItem build() {
            TradedItem tradedItem = new TradedItem(tradedItemId(trade.getId(), itemId(item.getId())));
            tradedItem.name = item.getName();
            tradedItem.owner = owningBidder;
            tradedItem.trade = trade;
            return tradedItem;
        }

    }

    static TradedItemBuilder traded(Item item) {
        return new TradedItemBuilder(item);
    }

}
