package com.kbhit.orangebox.trading.domain.repository;

import com.kbhit.orangebox.trading.domain.TradeId;
import com.kbhit.orangebox.trading.domain.Trade;
import org.springframework.data.repository.Repository;

public interface TradeRepository extends Repository<Trade,TradeId> {

    Trade findTradeById(TradeId tradeId);

    Trade save(Trade trade);

}
