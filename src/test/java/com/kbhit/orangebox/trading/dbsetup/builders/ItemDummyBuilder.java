package com.kbhit.orangebox.trading.dbsetup.builders;

import com.ninja_squad.dbsetup.operation.Operation;

import java.util.TreeMap;

import static com.kbhit.orangebox.trading.dbsetup.tables.ItemTable.ITEM_ID;
import static com.kbhit.orangebox.trading.dbsetup.tables.ItemTable.ITEM_NAME;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class ItemDummyBuilder {

    private TreeMap<String, Object> orderedValuesMap = new TreeMap<>();

    public ItemDummyBuilder withId(String bidderId) {
        orderedValuesMap.put(ITEM_ID.getColumnName(), bidderId);
        return this;
    }

    public ItemDummyBuilder withName(String name) {
        orderedValuesMap.put(ITEM_NAME.getColumnName(), name);
        return this;
    }

    public Operation build() {
        return insertInto("ITEMS")
                .columns(orderedValuesMap.keySet().stream().toArray(String[]::new))
                .values(orderedValuesMap.values().stream().toArray(Object[]::new)).build();
    }

    public static ItemDummyBuilder aDummyItem() {
        return new ItemDummyBuilder();
    }

}
