package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum ItemTable {

    ITEM_ID("ITEM_ID"),
    ITEM_NAME("NAME");

    String columnName;


    ItemTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(ItemTable::getColumnName).toArray(String[]::new);
    }

}
