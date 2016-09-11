package com.kbhit.orangebox.trading.domain;

import javax.persistence.*;

@Entity
@Table(name = "ITEMS")
public class Item {

    @EmbeddedId
    private ItemId id;

    @ManyToOne
    @JoinColumn(name = "bidId")
    private Bid bid;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Bidder owner;

    public ItemId getId() {
        return id;
    }


    public Bidder getOwner() {
        return owner;
    }
}
