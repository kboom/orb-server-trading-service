package com.kbhit.orangebox.trading;

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.deleteAllFrom;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.operation.CompositeOperation.sequenceOf;

public class TestDataSet {

    public static final Operation INSERT_TRADE_RESOURCES =
            sequenceOf(
                    insertInto("TRADES")
                            .columns("ID", "PLACED_DATE")
                            .values("USER_TRADES.GET_SOME_INCOMING", "/trades/incoming")
                            .build());

    public static final Operation DELETE_TRADE_RESOURCES = deleteAllFrom("API_RESOURCE");
}
