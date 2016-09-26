package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum BidTable {

    BID_ID("BID_ID"),
    PLACE_DATE("PLACE_DATE"),
    BIDDER_ID("BIDDER_ID"),
    TRADE_ID("TRADE_ID");

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
