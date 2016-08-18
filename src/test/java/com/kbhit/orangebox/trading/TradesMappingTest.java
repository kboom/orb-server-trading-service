package com.kbhit.orangebox.trading;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kbhit.orangebox.trading.domain.Bid;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TradesMappingTest extends IntegrationTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void canSerializeApiCatalog() {
        assertThat(objectMapper.canSerialize(Bid.class)).isTrue();
    }

}
