package com.kbhit.orangebox.trading.dbsetup.data;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.kbhit.orangebox.trading.dbsetup.builders.ItemDummyBuilder.aDummyItem;
import static com.ninja_squad.dbsetup.Operations.sequenceOf;

class InsertDummyItems {

    static final String RED_AGATHA_ITEM_ID = "RED AGATHA ITEM";
    static final String BLUE_AGATHA_ITEM_ID = "BLUE AGATHA ITEM";
    static final String RED_GREG_ITEM_ID = "RED GREG ITEM";
    static final String BLUE_GREG_ITEM_ID = "BLUE GREG ITEM";

    private static final Operation RED_AGATHA_ITEM = aDummyItem()
            .withItemId(RED_AGATHA_ITEM_ID)
            .withName("Red Agatha's item")
            .build();

    private static final Operation BLUE_AGATHA_ITEM = aDummyItem()
            .withItemId(BLUE_AGATHA_ITEM_ID)
            .withName("Blue Agatha's item")
            .build();

    private static final Operation RED_GREG_ITEM = aDummyItem()
            .withItemId(RED_GREG_ITEM_ID)
            .withName("Red Greg's item")
            .build();

    private static final Operation BLUE_GREG_ITEM = aDummyItem()
            .withItemId(BLUE_GREG_ITEM_ID)
            .withName("Blue Greg's item")
            .build();


    static Operation insertAll() {
        return sequenceOf(
                RED_AGATHA_ITEM,
                BLUE_AGATHA_ITEM,
                RED_GREG_ITEM,
                BLUE_GREG_ITEM
        );
    }

}
