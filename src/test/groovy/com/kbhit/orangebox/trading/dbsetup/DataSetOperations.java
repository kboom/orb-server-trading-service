package com.kbhit.orangebox.trading.dbsetup;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.kbhit.orangebox.trading.dbsetup.DbSetupUtils.DISABLE_FOREIGN_KEY_CHECKS;
import static com.kbhit.orangebox.trading.dbsetup.DbSetupUtils.ENABLE_FOREIGN_KEY_CHECKS;
import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;

class DataSetOperations {

    private static final Operation DELETE_BIDS = deleteAllFrom("BIDS");
    private static final Operation DELETE_TRADES = deleteAllFrom("TRADES");
    private static final Operation DELETE_BIDDERS = deleteAllFrom("BIDDERS");
    private static final Operation DELETE_ITEMS = deleteAllFrom("TRADED_ITEMS");
    private static final Operation DELETE_OFFERED_ITEMS = deleteAllFrom("OFFERED_ITEMS");
    private static final Operation DELETE_REQUESTED_ITEMS = deleteAllFrom("REQUESTED_ITEMS");

    static final Operation DELETE_ALL_DATA = Operations.sequenceOf(
            DISABLE_FOREIGN_KEY_CHECKS,
            Operations.truncate("TRADES", "BIDS", "BIDDERS", "TRADED_ITEMS", "OFFERED_ITEMS", "REQUESTED_ITEMS"),
            ENABLE_FOREIGN_KEY_CHECKS
    );

}
