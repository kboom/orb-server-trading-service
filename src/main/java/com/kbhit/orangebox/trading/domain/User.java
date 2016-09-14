package com.kbhit.orangebox.trading.domain;

import java.util.List;

public class User {
    private List<Authority> authorities;
    private boolean activated;
    private String password;
    private String username;

    public boolean getActivated() {
        return activated;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
