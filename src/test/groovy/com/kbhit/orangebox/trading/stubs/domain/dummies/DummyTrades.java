package com.kbhit.orangebox.trading.stubs.domain.dummies;

import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.BidderService;
import com.kbhit.orangebox.trading.domain.CounterParties;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.service.BiddingService;
import com.kbhit.orangebox.trading.domain.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

public class DummyTrades {

    @Autowired
    BiddingService biddingService;

    @Autowired
    StorageService storageService;

    @Autowired
    BidderService bidderService;

    @Autowired
    Actors actors;

    public Trade createEmptyTrade() {
        final Bidder requester = bidderService.getOrCreateBidder(actors.agatha);
        final Bidder responder = bidderService.getOrCreateBidder(actors.greg);
        CounterParties counterParties = new CounterParties(requester, responder);
        return biddingService.createTradeBetween(counterParties);
    }

}
