package com.kbhit.orangebox.trading;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class TestDataSet {

    public static final Operation INSERT_API_RESOURCES =
            sequenceOf(
                    insertInto("API_RESOURCE")
                            .columns("NAME", "URL")
                            .values("USER_TRADES.GET_SOME_INCOMING", "/trades/incoming")
                            .values("USER_TRADES.GET_SOME_OUTGOING", "/trades/outgoing")
                            .values("ITEMS.GET_ONE", "/items/<item_id>")
                            .values("ITEMS.GET_SOME", "/items")
                            .values("TRADE_ITEMS.GET_SOME_TRADE_ITEMS", "/box/<box_id>/traded")
                            .values("TRADE_ITEMS.GET_ALL_BY_IDS", "/trade-items")
                            .values("ITEM_CATEGORY.GET_ONE", "/categories/<category_id>")
                            .values("ITEM_CATEGORY.GET_ALL_PARENT", "/categories/parent")
                            .values("GATHERED_ITEMS.GET_SOME", "/items/gathered")
                            .values("BOXED_ITEMS.GET_SOME", "/box/<box_id>/items")
                            .values("TRADES.GET_TRADE", "/trade/<trade_id>")
                            .values("TRADES.POST_TRADE", "/trades")
                            .values("BIDS.GET_LATEST", "/trades/<trade_id>/bids/latest")
                            .values("TAGS.FIND_MATCHING", "/tags")
                            .build());

    public static final Operation DELETE_API_RESOURCES = deleteAllFrom("API_RESOURCE");
}
