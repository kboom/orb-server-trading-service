package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Item;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.common.collect.Sets.newHashSet;
import static com.kbhit.orangebox.trading.domain.Bid.buildBid;
import static org.assertj.core.api.Assertions.assertThat;

public class BidMappingTests extends MappingTest {

    private static final Item DUMMY_ITEM = new Item();
    private static final ItemDto DUMMY_ITEM_DTO = new ItemDto();

    @Autowired
    private Mapper mapper;

    @Test
    public void mapsRequestedItems() {
        Bid bid = buildBid()
                .withRequestedItems(newHashSet(DUMMY_ITEM)).build();
        assertThat(mapper.map(bid, BidDto.class).getRequestedItems()).containsExactly(DUMMY_ITEM_DTO);
    }

    @Test
    public void mapsOfferedItems() {
        Bid bid = buildBid()
                .withOfferedItems(newHashSet(DUMMY_ITEM)).build();
        assertThat(mapper.map(bid, BidDto.class).getOfferedItems()).containsExactly(DUMMY_ITEM_DTO);
    }

}
