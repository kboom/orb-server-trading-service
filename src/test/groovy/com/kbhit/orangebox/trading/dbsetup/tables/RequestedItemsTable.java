package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum RequestedItemsTable {

    BID_ID("BID_ID"),
    ITEM_ID("ITEM_ID"),
    TRADE_ID("TRADE_ID");

    String columnName;

    RequestedItemsTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(RequestedItemsTable::getColumnName).toArray(String[]::new);
    }

}
