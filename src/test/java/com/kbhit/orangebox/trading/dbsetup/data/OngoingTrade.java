package com.kbhit.orangebox.trading.dbsetup.data;

import com.kbhit.orangebox.trading.domain.TradeId;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;
import org.joda.time.DateTime;

import static com.kbhit.orangebox.trading.dbsetup.builders.BidDummyBuilder.aDummyBid;
import static com.kbhit.orangebox.trading.dbsetup.builders.TradeDummyBuilder.aDummyTrade;
import static com.kbhit.orangebox.trading.domain.BidderId.referenceBidder;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;

public class OngoingTrade {

    public static final TradeId ONGOING_TRADE_ID = referenceTrade("1");

    private static final Operation INITIAL_BID = aDummyBid()
            .withId(1)
            .withTradeId(ONGOING_TRADE_ID)
            .withPlaceDate(new DateTime("2016-01-04"))
            .withBidderId(referenceBidder("1"))
            .build();

    private static final Operation TRADE = aDummyTrade()
            .withId(ONGOING_TRADE_ID)
            .withRequester(DummyBidders.AGATA_BIDDER_ID)
            .withResponder(DummyBidders.GRZEGORZ_BIDDER_ID)
            .withCreateDate(new DateTime("2016-04-05"))
            .withUpdateDate(new DateTime("2016-06-02"))
            .withInitialBid(1)
            .withLatestBidId(3)
            .build();

    public Operation get() {
        return Operations.sequenceOf(INITIAL_BID, TRADE);
    }

}
