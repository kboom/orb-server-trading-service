package com.kbhit.orangebox.trading.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "BIDDERS")
public class Bidder {

    @EmbeddedId
    private BidderId id;

    private String login;

    private String firstName;

    private String lastName;

    public BidderId getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bidder bidder = (Bidder) o;

        return id.equals(bidder.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
