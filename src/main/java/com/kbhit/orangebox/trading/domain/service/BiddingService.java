package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.CounterParties;
import com.kbhit.orangebox.trading.domain.Trade;
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
    public Trade createTradeBetween(CounterParties counterParties) {
        Trade trade = tradeFactory.createTradeFor(counterParties);
        tradeRepository.save(trade);
        return trade;
    }

}
