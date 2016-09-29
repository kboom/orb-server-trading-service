package com.kbhit.orangebox.trading.stubs.domain.dummies

import com.kbhit.orangebox.trading.domain.BidderService
import com.kbhit.orangebox.trading.domain.Trade
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.kbhit.orangebox.trading.domain.Trade.aTrade
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade

@Component
class DummyTrades {

    @Autowired
    private DummyUsers dummyUsers

    @Autowired
    private DummyBids dummyBids

    @Autowired
    private BidderService bidderService

    Trade dummyInitialTrade() {
        def agathaBidder = bidderService.getOrCreateBidder(dummyUsers.agathaUser().build())
        def gregBidder = bidderService.getOrCreateBidder(dummyUsers.gregUser().build())
        return aTrade(referenceTrade("abc"))
                .withRequester(agathaBidder)
                .withResponder(gregBidder).build()
    }

    Trade dummyOngoingTrade() {
        Trade trade = dummyInitialTrade()
        trade.makeBid(dummyBids.dummyRequesterBidFor(trade).build())
        return trade
    }

}
