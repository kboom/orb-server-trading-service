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

}
