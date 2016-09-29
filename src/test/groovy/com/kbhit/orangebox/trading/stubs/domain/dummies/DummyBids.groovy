package com.kbhit.orangebox.trading.stubs.domain.dummies

import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.service.TimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import static com.kbhit.orangebox.trading.domain.Bid.BidBuilder
import static com.kbhit.orangebox.trading.domain.Bid.buildBidFor
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyItems.someItemsBelongingTo

@Component
class DummyBids {

    @Autowired
    private TimeService timeService

    BidBuilder dummyRequesterBidFor(Trade trade) {
        return buildBidFor(trade)
                .withBidder(trade.getRequester())
                .withRequestedItems(someItemsBelongingTo(trade.getResponder()))
                .withOfferedItems(someItemsBelongingTo(trade.getRequester()))
                .withPlaceDate(timeService.getCurrentTime())
    }

    BidBuilder dummyResponderBidFor(Trade trade) {
        return buildBidFor(trade)
                .withBidder(trade.getResponder())
                .withRequestedItems(someItemsBelongingTo(trade.getRequester()))
                .withOfferedItems(someItemsBelongingTo(trade.getResponder()))
                .withPlaceDate(timeService.getCurrentTime())
    }

}
