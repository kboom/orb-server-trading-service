package com.kbhit.orangebox.trading.domain.factory;

import com.kbhit.orangebox.trading.domain.CounterParties;
import com.kbhit.orangebox.trading.domain.Trade;
import com.kbhit.orangebox.trading.domain.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kbhit.orangebox.trading.domain.Trade.aTrade;

public class TradeFactory {

    @Autowired
    private TradeIdGenerator idGenerator;

    @Autowired
    private TimeService timeService;

    public Trade createTradeFor(CounterParties counterParties) {
        return aTrade(idGenerator.generateId())
                .createdOn(timeService.getCurrentTime())
                .withRequester(counterParties.getRequester())
                .withResponder(counterParties.getResponder())
                .build();
    }

}
