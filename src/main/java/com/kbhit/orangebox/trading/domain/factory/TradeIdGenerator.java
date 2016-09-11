package com.kbhit.orangebox.trading.domain.factory;

import com.kbhit.orangebox.trading.domain.TradeId;

import java.util.UUID;

import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static java.util.UUID.randomUUID;

public class TradeIdGenerator {

    public TradeId generateId() {
        UUID randomId = randomUUID();
        return referenceTrade(randomId.toString());
    }

}
