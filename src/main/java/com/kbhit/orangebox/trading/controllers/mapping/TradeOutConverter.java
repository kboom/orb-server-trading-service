package com.kbhit.orangebox.trading.controllers.mapping;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.domain.Trade;
import org.springframework.stereotype.Component;

@Component
public final class TradeOutConverter extends AbstractDomainConverter<Trade, TradeDto> {

    public TradeOutConverter() {
        super(Trade.class, TradeDto.class);
    }

    @Override
    public TradeDto convertFrom(Trade source) {
        return new TradeDto();
    }

}
