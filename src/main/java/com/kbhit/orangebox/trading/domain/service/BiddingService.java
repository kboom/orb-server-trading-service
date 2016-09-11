package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.TradeId;
import com.kbhit.orangebox.trading.domain.factory.TradeFactory;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiddingService {

    @Autowired
    private TradeFactory tradeFactory;

    public Trade createTradeFor(Bid initialBid) {
        return tradeFactory.createTradeFor(initialBid);
    }

    public Trade postBidFor(TradeId tradeId, Bid bid) {
        return null;
    }

}
