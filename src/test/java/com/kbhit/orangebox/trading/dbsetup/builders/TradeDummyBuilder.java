package com.kbhit.orangebox.trading.dbsetup.builders;

import com.kbhit.orangebox.trading.domain.BidderId;
import com.kbhit.orangebox.trading.domain.TradeId;
import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.ReadableDateTime;

import java.sql.Timestamp;
import java.util.TreeMap;

import static com.kbhit.orangebox.trading.dbsetup.tables.TradeTable.*;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class TradeDummyBuilder {

    private TreeMap<String, Object> orderedValuesMap = new TreeMap<>();

    public TradeDummyBuilder withId(String tradeId) {
        orderedValuesMap.put(TRADE_ID.getColumnName(), tradeId);
        return this;
    }

    public TradeDummyBuilder withCreateDate(ReadableDateTime createDate) {
        orderedValuesMap.put(CREATE_DATE.getColumnName(), new Timestamp(createDate.getMillis()));
        return this;
    }

    public TradeDummyBuilder withUpdateDate(ReadableDateTime updateDate) {
        orderedValuesMap.put(UPDATE_DATE.getColumnName(), new Timestamp(updateDate.getMillis()));
        return this;
    }

    public TradeDummyBuilder withInitialBid(Integer bidId) {
        orderedValuesMap.put(INITIAL_BID_ID.getColumnName(), bidId);
        return this;
    }

    public TradeDummyBuilder withLatestBidId(Integer bidId) {
        orderedValuesMap.put(LATEST_BID_ID.getColumnName(), bidId);
        return this;
    }

    public TradeDummyBuilder withRequester(String requester) {
        orderedValuesMap.put(REQUESTER_ID.getColumnName(), requester);
        return this;
    }

    public TradeDummyBuilder withResponder(String responder) {
        orderedValuesMap.put(RESPONDER_ID.getColumnName(), responder);
        return this;
    }

    public Operation build() {
        return insertInto("TRADES")
                .columns(orderedValuesMap.keySet().stream().toArray(String[]::new))
                .values(orderedValuesMap.values().stream().toArray(Object[]::new)).build();
    }

    public static TradeDummyBuilder aDummyTrade() {
        return new TradeDummyBuilder();
    }

}
