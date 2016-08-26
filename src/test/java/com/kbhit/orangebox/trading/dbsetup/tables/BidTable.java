package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum BidTable {

    BID_ID("ID"),
    PLACE_DATE("CREATE_DATE"),
    BIDDER_ID("UPDATE_DATE"),
    TRADE_ID("INITIAL_BID_ID");

    String columnName;


    BidTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(BidTable::getColumnName).toArray(String[]::new);
    }

}
