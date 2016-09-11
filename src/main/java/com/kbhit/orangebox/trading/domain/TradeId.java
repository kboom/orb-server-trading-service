package com.kbhit.orangebox.trading.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TradeId implements Serializable {

    private String tradeId;

    private TradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradeId tradeId = (TradeId) o;

        return this.tradeId != null ? this.tradeId.equals(tradeId.tradeId) : tradeId.tradeId == null;

    }

    @Override
    public int hashCode() {
        return tradeId != null ? tradeId.hashCode() : 0;
    }

    @SuppressWarnings("unused")
    TradeId() {
    }

    public static TradeId referenceTrade(String id) {
        return new TradeId(id);
    }

    public String rawValue() {
        return tradeId;
    }

}
