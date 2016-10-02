package com.kbhit.orangebox.trading.tests.behaviour

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.domain.Bid
import com.kbhit.orangebox.trading.domain.BidderService
import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.repository.TradeRepository
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.StorageService
import com.kbhit.orangebox.trading.stubs.ConfigurableTimeService
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyBids
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyItems
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyTrades
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers
import org.springframework.beans.factory.annotation.Autowired

import static org.assertj.core.api.Assertions.assertThat

public class BiddingSpec extends BehaviourSpecification {

    @Autowired
    TradeRepository tradeRepository

    @Autowired
    BiddingService biddingService

    @Autowired
    StorageService storageService

    @Autowired
    BidderService bidderService

    @Autowired
    ConfigurableTimeService timeService

    @Autowired
    DummyTrades dummyTrades

    @Autowired
    DummyBids dummyBids

    @Autowired
    DummyItems dummyItems

    @Autowired
    DummyUsers dummyUsers


    def "Bid becomes initial when posted to a new trade"() {
        given:
        Trade trade = dummyTrades.dummyInitialTrade()
        Bid bid = dummyBids.dummyRequesterBidFor(trade).build()

        when:
        trade.makeBid(bid)

        then:
        assertThat(trade.getInitialBid()).isEqualTo(bid)
    }

    def "Bid becomes latest when posted to a trade but is not made initial"() {
        given:
        Trade trade = dummyTrades.dummyOngoingTrade()
        Bid bid = dummyBids.dummyResponderBidFor(trade).build()

        when:
        trade.makeBid(bid)

        then:
        assertThat(trade.getLatestBid()).isEqualTo(bid)
        assertThat(trade.getInitialBid()).isNotEqualTo(bid)
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
        testDataLoader.createDummyTrade();
    }

}
