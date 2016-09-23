package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.TradeId;
import com.kbhit.orangebox.trading.domain.factory.TradeFactory;
import com.kbhit.orangebox.trading.domain.repository.BidRepository;
import com.kbhit.orangebox.trading.domain.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class BiddingService {

    @Autowired
    private TradeFactory tradeFactory;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private BidRepository bidRepository;

    @Transactional
    public Trade createTradeFor(Bid initialBid) {
        Trade trade = tradeFactory.createTradeFor(initialBid);
        bidRepository.save(initialBid);
        tradeRepository.save(trade);
        return trade;
    }

    @Transactional
    public Trade postBidFor(TradeId tradeId, Bid bid) {
        Trade trade = tradeRepository.findTradeById(tradeId);
        trade.makeBid(bid);
        bidRepository.save(bid);
        tradeRepository.save(trade);
        return trade;
    }

}
