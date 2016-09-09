package com.kbhit.orangebox.trading.dbsetup.data;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;

import static com.kbhit.orangebox.trading.dbsetup.builders.BidDummyBuilder.aDummyBid;
import static com.kbhit.orangebox.trading.dbsetup.builders.TradeDummyBuilder.aDummyTrade;
import static com.kbhit.orangebox.trading.dbsetup.data.DummyBidders.AGATA_BIDDER_ID;
import static com.kbhit.orangebox.trading.dbsetup.data.DummyBidders.GRZEGORZ_BIDDER_ID;
import static com.ninja_squad.dbsetup.Operations.sql;

public class OngoingTrade {

    static final String ONGOING_TRADE_ID = "1";
    static final Integer INITIAL_BID_ID = 1;
    static final Integer LATEST_BID_ID = 2;


    private static final Operation INITIAL_BID = aDummyBid()
            .withId(INITIAL_BID_ID)
            .withTradeId(ONGOING_TRADE_ID)
            .withPlaceDate(new DateTime("2016-01-04"))
            .withBidderId(AGATA_BIDDER_ID)
            .build();

    private static final Operation LATEST_BID = aDummyBid()
            .withId(LATEST_BID_ID)
            .withTradeId(ONGOING_TRADE_ID)
            .withPlaceDate(new DateTime("2016-01-04"))
            .withBidderId(AGATA_BIDDER_ID)
            .build();

    private static final Operation TRADE = aDummyTrade()
            .withId(ONGOING_TRADE_ID)
            .withRequester(AGATA_BIDDER_ID)
            .withResponder(GRZEGORZ_BIDDER_ID)
            .withCreateDate(new DateTime("2016-04-05"))
            .withUpdateDate(new DateTime("2016-06-02"))
            .build();

    public static Operation get() {
        return Operations.sequenceOf(TRADE, INITIAL_BID, LATEST_BID,
                setInitialBidForTrade(ONGOING_TRADE_ID, INITIAL_BID_ID),
                setLatestBidForTrade(ONGOING_TRADE_ID, LATEST_BID_ID));
    }

    private static Operation setLatestBidForTrade(String tradeId, Integer bidId) {
        return sql(String.format("UPDATE TRADES SET LATEST_BID_ID = %d where trade_id = %s", bidId, tradeId));
    }

    private static Operation setInitialBidForTrade(String tradeId, Integer bidId) {
        return sql(String.format("UPDATE TRADES SET INITIAL_BID_ID = %d where trade_id = %s", bidId, tradeId));
    }

}
