package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum OfferedItemsTable {

    ITEM_ID("ITEM_ID"),
    BID_ID("BID_ID");

    String columnName;

    OfferedItemsTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(OfferedItemsTable::getColumnName).toArray(String[]::new);
    }

}

