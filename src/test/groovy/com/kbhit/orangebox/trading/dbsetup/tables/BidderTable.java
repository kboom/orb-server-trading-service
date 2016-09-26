package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum BidderTable {

    BIDDER_ID("BIDDER_ID"),
    FIRST_NAME("FIRST_NAME"),
    LAST_NAME("LAST_NAME"),
    LOGIN("LOGIN");

    String columnName;

    BidderTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(BidderTable::getColumnName).toArray(String[]::new);
    }

}
