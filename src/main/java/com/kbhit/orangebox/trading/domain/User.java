package com.kbhit.orangebox.trading.domain;

import java.util.List;
import java.util.stream.StreamSupport;

public class User {
    private List<Authority> authorities;
    private boolean activated;
    private String password;

    public boolean getActivated() {
        return activated;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }
}
