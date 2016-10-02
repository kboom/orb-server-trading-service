package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.User;

public class Item {

    private String id;
    private String name;
    private User owner;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

}
