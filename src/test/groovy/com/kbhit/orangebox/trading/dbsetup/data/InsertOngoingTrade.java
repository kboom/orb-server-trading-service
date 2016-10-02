package com.kbhit.orangebox.trading.dbsetup.data;

import com.kbhit.orangebox.trading.dbsetup.tables.OfferedItemsTable;
import com.kbhit.orangebox.trading.dbsetup.tables.RequestedItemsTable;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Insert;
import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;

import static com.google.common.collect.Lists.newArrayList;
import static com.kbhit.orangebox.trading.dbsetup.DbSetupUtils.DISABLE_FOREIGN_KEY_CHECKS;
import static com.kbhit.orangebox.trading.dbsetup.DbSetupUtils.ENABLE_FOREIGN_KEY_CHECKS;
import static com.kbhit.orangebox.trading.dbsetup.builders.BidDummyBuilder.aDummyBid;
import static com.kbhit.orangebox.trading.dbsetup.builders.TradeDummyBuilder.aDummyTrade;
import static com.kbhit.orangebox.trading.dbsetup.data.InsertDummyBidders.AGATA_BIDDER_ID;
import static com.kbhit.orangebox.trading.dbsetup.data.InsertDummyBidders.GRZEGORZ_BIDDER_ID;
import static com.kbhit.orangebox.trading.dbsetup.data.InsertDummyItems.*;
import static com.kbhit.orangebox.trading.dbsetup.tables.RequestedItemsTable.TRADE_ID;
import static com.kbhit.orangebox.trading.dbsetup.tables.RequestedItemsTable.allColumns;
import static com.ninja_squad.dbsetup.Operations.insertInto;
import static com.ninja_squad.dbsetup.Operations.sql;
import static java.lang.String.format;

public class InsertOngoingTrade {

    public static final String ONGOING_TRADE_ID = "1";
    private static final Integer INITIAL_BID_ID = 1;
    private static final Integer LATEST_BID_ID = 2;


    private static final Operation INSERT_INITIAL_BID = aDummyBid()
            .withId(INITIAL_BID_ID)
            .withTradeId(ONGOING_TRADE_ID)
            .withPlaceDate(new DateTime("2016-01-04"))
            .withBidderId(AGATA_BIDDER_ID)
            .build();

    private static final Operation INSERT_LATEST_BID = aDummyBid()
            .withId(LATEST_BID_ID)
            .withTradeId(ONGOING_TRADE_ID)
            .withPlaceDate(new DateTime("2016-01-04"))
            .withBidderId(GRZEGORZ_BIDDER_ID)
            .build();

    private static final Operation INSERT_TRADE = aDummyTrade()
            .withId(ONGOING_TRADE_ID)
            .withRequester(AGATA_BIDDER_ID)
            .withResponder(GRZEGORZ_BIDDER_ID)
            .withCreateDate(new DateTime("2016-04-05"))
            .withUpdateDate(new DateTime("2016-06-02"))
            .build();

    public static Operation insertAll() {
        return Operations.sequenceOf(
                DISABLE_FOREIGN_KEY_CHECKS,
                INSERT_TRADE,
                INSERT_INITIAL_BID,
                INSERT_LATEST_BID,
                InsertDummyItems.insertAll(ONGOING_TRADE_ID),
                setRequestedItemsFor(INITIAL_BID_ID, BLUE_GREG_ITEM_ID),
                setOfferedItemsFor(INITIAL_BID_ID, RED_AGATHA_ITEM_ID),
                setRequestedItemsFor(LATEST_BID_ID, RED_AGATHA_ITEM_ID, BLUE_AGATHA_ITEM_ID),
                setOfferedItemsFor(LATEST_BID_ID, BLUE_GREG_ITEM_ID, RED_GREG_ITEM_ID),
                setInitialBidForTrade(ONGOING_TRADE_ID, INITIAL_BID_ID),
                setLatestBidForTrade(ONGOING_TRADE_ID, LATEST_BID_ID),
                ENABLE_FOREIGN_KEY_CHECKS
        );
    }

    private static Operation setRequestedItemsFor(Integer bidId, String... itemIds) {
        return bindRequestedItemsToBid(bidId, itemIds);
    }

    private static Operation setOfferedItemsFor(Integer bidId, String... itemIds) {
        return bindOfferedItemsToBid(bidId, itemIds);
    }

    private static Operation setLatestBidForTrade(String tradeId, Integer bidId) {
        return sql(format("UPDATE TRADES SET LATEST_BID_ID = %d where trade_id = %s", bidId, tradeId));
    }

    private static Operation setInitialBidForTrade(String tradeId, Integer bidId) {
        return sql(format("UPDATE TRADES SET INITIAL_BID_ID = %d where trade_id = %s", bidId, tradeId));
    }

    private static Operation bindRequestedItemsToBid(Integer bidId, String... itemIds) {
        Insert.Builder insertBuilder = insertInto("REQUESTED_ITEMS").columns(RequestedItemsTable.allColumns());
        newArrayList(itemIds).forEach(id -> insertBuilder.values(bidId, id, ONGOING_TRADE_ID));
        return insertBuilder.build();
    }

    private static Operation bindOfferedItemsToBid(Integer bidId, String... itemIds) {
        Insert.Builder insertBuilder = insertInto("OFFERED_ITEMS").columns(OfferedItemsTable.allColumns());
        newArrayList(itemIds).forEach(id -> insertBuilder.values(bidId, id, ONGOING_TRADE_ID));
        return insertBuilder.build();
    }

}
