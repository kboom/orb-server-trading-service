package com.kbhit.orangebox.trading.dbsetup.data;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.kbhit.orangebox.trading.dbsetup.builders.ItemDummyBuilder.aDummyItem;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

class InsertDummyItems {

    static final String RED_AGATHA_ITEM_ID = "RED AGATHA ITEM";
    static final String BLUE_AGATHA_ITEM_ID = "BLUE AGATHA ITEM";
    static final String RED_GREG_ITEM_ID = "RED GREG ITEM";
    static final String BLUE_GREG_ITEM_ID = "BLUE GREG ITEM";

    private static Operation redAgathaItem(String tradeId) {
        return aDummyItem()
                .withItemId(RED_AGATHA_ITEM_ID)
                .withTradeId(tradeId)
                .withName("Red Agatha's item")
                .build();
    }

    private static Operation blueAgathaItem(String tradeId) {
        return aDummyItem()
                .withItemId(BLUE_AGATHA_ITEM_ID)
                .withTradeId(tradeId)
                .withName("Blue Agatha's item")
                .build();
    }

    private static Operation redGregItem(String tradeId) {
        return aDummyItem()
                .withItemId(RED_GREG_ITEM_ID)
                .withTradeId(tradeId)
                .withName("Red Greg's item")
                .build();
    }

    private static Operation blueGregItem(String tradeId) {
        return aDummyItem()
                .withItemId(BLUE_GREG_ITEM_ID)
                .withTradeId(tradeId)
                .withName("Blue Greg's item")
                .build();
    }


    static Operation insertAll(String tradeId) {
        return sequenceOf(
                redAgathaItem(tradeId),
                blueAgathaItem(tradeId),
                redGregItem(tradeId),
                blueGregItem(tradeId)
        );
    }

}
