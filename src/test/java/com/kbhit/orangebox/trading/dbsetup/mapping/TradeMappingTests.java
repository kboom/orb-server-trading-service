package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.Trade;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kbhit.orangebox.trading.domain.Bid.buildBid;
import static com.kbhit.orangebox.trading.domain.Trade.aTrade;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static org.assertj.core.api.Assertions.assertThat;


public class TradeMappingTests extends MappingTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void mapsTrade() {
        Bidder bidder = new Bidder();
        Bid bid = buildBid().build();
        Trade trade = aTrade(referenceTrade("abc"))
                .withRequester(bidder)
                .withInitialBid(bid)
                .build();
        assertThat(mapper.map(trade, TradeDto.class))
                .hasFieldOrPropertyWithValue("id", "abc");
    }

}
