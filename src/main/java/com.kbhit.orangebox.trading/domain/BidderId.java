package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;

@Embeddable
public class BidderId {

    private Long id;

    BidderId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidderId bidderId = (BidderId) o;

        return id != null ? id.equals(bidderId.id) : bidderId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static BidderId bidderId(long id) {
        return new BidderId(id);
    }

}
