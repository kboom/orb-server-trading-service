package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BidderId implements Serializable {

    private String id;

    BidderId(String id) {
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

    public static BidderId bidderId(String id) {
        return new BidderId(id);
    }

}
