package com.kbhit.orangebox.trading.dbsetup;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

class DataSetOperations {

    private static final Operation DELETE_BIDS = deleteAllFrom("BIDS");
    private static final Operation DELETE_TRADES = deleteAllFrom("TRADES");
    private static final Operation DELETE_BIDDERS = deleteAllFrom("BIDDERS");
    private static final Operation DELETE_ITEMS = deleteAllFrom("ITEMS");

    static final Operation DELETE_ALL_DATA = Operations.sequenceOf(DELETE_TRADES, DELETE_BIDS, DELETE_ITEMS, DELETE_BIDDERS);

}
