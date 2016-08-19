package com.kbhit.orangebox.trading;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class TestDataSet {

    public static final Operation INSERT_TRADE_RESOURCES =
            sequenceOf();

    public static final Operation DELETE_BIDS = deleteAllFrom("BIDS");
    public static final Operation DELETE_TRADES = deleteAllFrom("TRADES");
    public static final Operation DELETE_BIDDERS = deleteAllFrom("BIDDERS");
    public static final Operation DELETE_ITEMS = deleteAllFrom("ITEMS");

    public static final Operation DELETE_ALL_DATA = Operations.sequenceOf(DELETE_TRADES, DELETE_BIDS, DELETE_ITEMS, DELETE_BIDDERS);
}
