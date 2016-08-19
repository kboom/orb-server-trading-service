package com.kbhit.orangebox.trading.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
public class Item {

    @EmbeddedId
    private ItemId id;

    private String name;

}
