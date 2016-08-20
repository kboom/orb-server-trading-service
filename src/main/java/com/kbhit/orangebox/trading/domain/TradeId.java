package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TradeId implements Serializable {

    private String id;

    TradeId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeId tradeId = (TradeId) o;

        return id != null ? id.equals(tradeId.id) : tradeId.id == null;

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public static TradeId referenceTrade(String id) {
        return new TradeId(id);
    }

}
