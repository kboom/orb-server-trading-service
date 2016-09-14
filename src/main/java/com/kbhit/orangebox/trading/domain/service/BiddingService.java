package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.TradeId;
import com.kbhit.orangebox.trading.domain.factory.TradeFactory;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BiddingService {

    @Autowired
    private TradeFactory tradeFactory;

    @Transactional
    public Trade createTradeFor(Bid initialBid) {
        return tradeFactory.createTradeFor(initialBid);
    }

    @Transactional
    public Trade postBidFor(TradeId tradeId, Bid bid) {
        return null;
    }

}
