package com.kbhit.orangebox.trading.dbsetup.builders;

import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.ReadableDateTime;

import java.sql.Timestamp;
import java.util.TreeMap;

import static com.kbhit.orangebox.trading.dbsetup.tables.BidTable.*;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class BidDummyBuilder {

    private TreeMap<String, Object> orderedValuesMap = new TreeMap<>();

    public BidDummyBuilder withId(int id) {
        orderedValuesMap.put(BID_ID.getColumnName(), id);
        return this;
    }

    public BidDummyBuilder withPlaceDate(ReadableDateTime dateTime) {
        orderedValuesMap.put(PLACE_DATE.getColumnName(), new Timestamp(dateTime.getMillis()));
        return this;
    }

    public BidDummyBuilder withBidderId(String bidderId) {
        orderedValuesMap.put(BIDDER_ID.getColumnName(), bidderId);
        return this;
    }

    public BidDummyBuilder withTradeId(String tradeId) {
        orderedValuesMap.put(TRADE_ID.getColumnName(), tradeId);
        return this;
    }

    public Operation build() {
        return insertInto("BIDS")
                .columns(orderedValuesMap.keySet().stream().toArray(String[]::new))
                .values(orderedValuesMap.values().stream().toArray(Object[]::new)).build();
    }

    public static BidDummyBuilder aDummyBid() {
        return new BidDummyBuilder();
    }

}
