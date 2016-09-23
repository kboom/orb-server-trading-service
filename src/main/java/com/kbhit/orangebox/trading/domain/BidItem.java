package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.service.Item;

import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
public class BidItem {

    @EmbeddedId
    private ItemId id;

    @ManyToOne
    @JoinColumn(name = "bid_id")
    private Bid bid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Bidder owner;

    public BidItem(Item item, Bidder owner, Bid bid) {
        this.id = ItemId.itemId(item.getId());
        this.bid = bid;
        this.owner = owner;
        this.name = item.getName();
    }

    public ItemId getId() {
        return id;
    }


    public Bidder getOwner() {
        return owner;
    }

    public Bid getBid() {
        return bid;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    BidItem() {

    }

}
