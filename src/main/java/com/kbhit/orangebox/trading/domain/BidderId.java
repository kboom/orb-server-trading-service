package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BidderId implements Serializable {

    private String bidderId;

    private BidderId(String bidderId) {
        this.bidderId = bidderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidderId bidderId = (BidderId) o;

        return this.bidderId != null ? this.bidderId.equals(bidderId.bidderId) : bidderId.bidderId == null;

    }

    @Override
    public int hashCode() {
        return bidderId != null ? bidderId.hashCode() : 0;
    }

    public static BidderId referenceBidder(String id) {
        return new BidderId(id);
    }

    @SuppressWarnings("unused")
    BidderId() {
    }

}
