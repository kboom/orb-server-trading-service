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

    public ItemId getId() {
        return id;
    }



}
