package com.kbhit.orangebox.trading.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @SequenceGenerator(name = "bid_seq", initialValue = 10000, allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_seq")
    private Long id;

    private Date placeDate;

    @ManyToOne
    private Bidder bidder;

    @OneToMany
    private Set<Item> offeredItems;

    @OneToMany
    private Set<Item> requestedItems;

}
