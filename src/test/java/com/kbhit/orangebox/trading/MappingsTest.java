package com.kbhit.orangebox.trading;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbhit.orangebox.trading.domain.TradedItem;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.Trade;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MappingsTest extends IntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void canSerializeBid() {
        assertThat(objectMapper.canSerialize(Bid.class)).isTrue();
    }

    @Test
    public void canSerializeTrade() {
        assertThat(objectMapper.canSerialize(Trade.class)).isTrue();
    }

    @Test
    public void canSerializeBidder() {
        assertThat(objectMapper.canSerialize(Bidder.class)).isTrue();
    }

    @Test
    public void canSerializeItem() {
        assertThat(objectMapper.canSerialize(TradedItem.class)).isTrue();
    }

}
