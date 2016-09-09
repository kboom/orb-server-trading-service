package com.kbhit.orangebox.trading.dbsetup.tables;

import java.util.Arrays;

public enum TradeTable {

    TRADE_ID("TRADE_ID"),
    CREATE_DATE("CREATE_DATE"),
    UPDATE_DATE("UPDATE_DATE"),
    INITIAL_BID_ID("INITIAL_BID_ID"),
    LATEST_BID_ID("LATEST_BID_ID"),
    REQUESTER_ID("REQUESTER_ID"),
    RESPONDER_ID("RESPONDER_ID");

    String columnName;


    TradeTable(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

    public static String[] allColumns() {
        return Arrays.stream(values()).map(TradeTable::getColumnName).toArray(String[]::new);
    }

}
