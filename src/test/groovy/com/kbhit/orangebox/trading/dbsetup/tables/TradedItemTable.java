package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum TradedItemTable {

    ITEM_ID("ITEM_ID"),
    TRADE_ID("TRADE_ID"),
    ITEM_NAME("NAME"),
    OWNER_ID("OWNER_ID");

    String columnName;


    TradedItemTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(TradedItemTable::getColumnName).toArray(String[]::new);
    }

}
