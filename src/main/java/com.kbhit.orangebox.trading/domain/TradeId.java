package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;

@Embeddable
public class TradeId {

    private Long id;

    TradeId(Long id) {
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

    public static TradeId tradeId(long id) {
        return new TradeId(id);
    }

}
