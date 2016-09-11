package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.Item;
import com.kbhit.orangebox.trading.domain.Trade;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kbhit.orangebox.trading.domain.Bid.buildBid;
import static com.kbhit.orangebox.trading.domain.Trade.aTrade;
import static com.kbhit.orangebox.trading.domain.TradeId.referenceTrade;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;


public class TradeMappingTests extends MappingTest {

    private static final Item ANY_ITEM = new Item();
    private static final String ANY_TRADE_ID = "abc";

    @Autowired
    private Mapper mapper;

    @Test
    public void mapsEveryProperty() {
        Bidder bidder = new Bidder();
        Bid bid = buildBid()
                .withOfferedItems(singletonList(ANY_ITEM))
                .withRequestedItems(singletonList(ANY_ITEM))
                .build();

        Trade trade = aTrade(referenceTrade(ANY_TRADE_ID))
                .withRequester(bidder)
                .withInitialBid(bid)
                .build();

        assertThat(mapper.map(trade, TradeDto.class))
                .hasNoNullFieldsOrProperties()
                .extracting("historicBids").hasSize(1);
    }

    @Test
    public void mapsId() {
        Trade trade = aTrade(referenceTrade("abc")).build();

        assertThat(mapper.map(trade, TradeDto.class))
                .hasFieldOrPropertyWithValue("id", "abc");
    }

}
