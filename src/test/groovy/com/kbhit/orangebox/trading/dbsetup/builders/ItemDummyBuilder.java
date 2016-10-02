package com.kbhit.orangebox.trading.dbsetup.builders;

import com.ninja_squad.dbsetup.operation.Operation;

import java.util.TreeMap;

import static com.kbhit.orangebox.trading.dbsetup.tables.TradedItemTable.*;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class ItemDummyBuilder {

    private TreeMap<String, Object> orderedValuesMap = new TreeMap<>();

    public ItemDummyBuilder withItemId(String itemId) {
        orderedValuesMap.put(ITEM_ID.getColumnName(), itemId);
        return this;
    }

    public ItemDummyBuilder withOwnerId(String ownerId) {
        orderedValuesMap.put(OWNER_ID.getColumnName(), ownerId);
        return this;
    }

    public ItemDummyBuilder withName(String name) {
        orderedValuesMap.put(ITEM_NAME.getColumnName(), name);
        return this;
    }

    public ItemDummyBuilder withTradeId(String tradeId) {
        orderedValuesMap.put(TRADE_ID.getColumnName(), tradeId);
        return this;
    }

    public Operation build() {
        return insertInto("TRADED_ITEMS")
                .columns(orderedValuesMap.keySet().stream().toArray(String[]::new))
                .values(orderedValuesMap.values().stream().toArray(Object[]::new)).build();
    }

    public static ItemDummyBuilder aDummyItem() {
        return new ItemDummyBuilder();
    }
}
