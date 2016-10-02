package com.kbhit.orangebox.trading.domain;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class TradedItemId implements Serializable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "itemId", column = @Column(name = "item_id"))
    })
    private ItemId itemId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "tradeId", column = @Column(name = "trade_id"))
    })
    private TradeId tradeId;

    private TradedItemId(TradeId tradeId, ItemId itemId) {
        this.tradeId = tradeId;
        this.itemId = itemId;
    }

    public static TradedItemId tradedItemId(TradeId tradeId, ItemId itemId) {
        return new TradedItemId(tradeId, itemId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TradedItemId tradedItemId = (TradedItemId) o;

        if (!itemId.equals(tradedItemId.itemId)) return false;
        return tradeId.equals(tradedItemId.tradeId);

    }

    @Override
    public int hashCode() {
        int result = itemId.hashCode();
        result = 31 * result + tradeId.hashCode();
        return result;
    }

    public ItemId getItemId() {
        return itemId;
    }

    @SuppressWarnings("unused")
    TradedItemId() {
    }
}
