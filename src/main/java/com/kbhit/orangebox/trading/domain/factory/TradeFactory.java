package com.kbhit.orangebox.trading.domain.factory;

import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.BidItem;
import com.kbhit.orangebox.trading.domain.Trade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static com.kbhit.orangebox.trading.domain.Trade.aTrade;

public class TradeFactory {

    @Autowired
    private TradeIdGenerator idGenerator;

    public Trade createTradeFor(Bid initialBid) {
        return aTrade(idGenerator.generateId())
                .withInitialBid(initialBid)
                .withRequester(initialBid.getBidder())
                .withResponder(resolveResponderFrom(initialBid))
                .build();
    }

    private Bidder resolveResponderFrom(Bid initialBid) {
        Set<BidItem> requestedItems = initialBid.getRequestedItems();
        return requestedItems.iterator().next().getOwner();
    }

}
