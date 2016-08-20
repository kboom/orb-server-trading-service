package com.kbhit.orangebox.trading.controllers.mapping;

import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.domain.Bid;
import org.springframework.stereotype.Component;

@Component
public final class BidOutConverter extends AbstractDomainConverter<Bid, BidDto> {

    public BidOutConverter() {
        super(Bid.class, BidDto.class);
    }

    @Override
    public BidDto convertFrom(Bid source) {
        return null;
    }

}
