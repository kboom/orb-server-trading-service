package com.kbhit.orangebox.trading.behaviour

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyBids
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyItems
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyTrades
import com.kbhit.orangebox.trading.dbsetup.data.InsertOngoingTrade
import com.kbhit.orangebox.trading.domain.Bid
import com.kbhit.orangebox.trading.domain.BidderService
import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.TradeId
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.StorageService
import com.kbhit.orangebox.trading.stubs.ConfigurableTimeService
import org.springframework.beans.factory.annotation.Autowired

import static com.google.common.collect.Lists.newArrayList
import static com.kbhit.orangebox.trading.domain.Bid.buildBidFor
import static org.assertj.core.api.Assertions.assertThat

public class BiddingSpec extends BehaviourSpecification {

    @Autowired
    BiddingService biddingService;

    @Autowired
    StorageService storageService;

    @Autowired
    BidderService bidderService;

    @Autowired
    ConfigurableTimeService timeService;

    @Autowired
    DummyTrades dummyTrades;

    @Autowired
    DummyBids dummyBids

    @Autowired
    DummyItems dummyItems;


    def "Bid becomes initial when posted to a new trade"() {
        given:
        Trade emptyTrade = dummyTrades.createEmptyTrade()
        Bid initialBid = dummyBids.dummyBid().with().build();



                buildBidFor(emptyTrade)
                .withBidder(emptyTrade.getRequester())
                .withOfferedItems(newArrayList(dummyItems.dummyItemOwnedBy(agatha)))
                .withRequestedItems(newArrayList(DummyItems.firstAgathaItem))
                .build();

        when:
        Trade trade = biddingService.postBidFor(TradeId.referenceTrade(InsertOngoingTrade.ONGOING_TRADE_ID), initialBid)

        then:
        assertThat(trade.getLatestBid()).isEqualTo(initialBid);
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
        testDataLoader.createDummyTrade();
    }

}
