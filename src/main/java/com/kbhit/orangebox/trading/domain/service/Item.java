package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.ItemId;
import com.kbhit.orangebox.trading.domain.User;

public class Item {
    private String id;
    private String name;
    private User owner;

    public ItemId getId() {
        return ItemId.itemId(id);
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }
}
