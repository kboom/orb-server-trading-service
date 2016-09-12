package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.domain.Bid;
import com.kbhit.orangebox.trading.domain.Item;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import static com.google.common.collect.Sets.newHashSet;
import static com.kbhit.orangebox.trading.domain.Bid.buildBid;
import static com.kbhit.orangebox.trading.domain.ItemId.itemId;
import static org.assertj.core.api.Assertions.assertThat;

public class BidMappingTests extends MappingTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void mapsRequestedItems() {
        Bid bid = buildBid()
                .withRequestedItems(newHashSet(createItem("a"), createItem("b")))
                .build();
        assertThat(mapper.map(bid, BidDto.class).getRequestedItems()).extracting("id").containsExactlyInAnyOrder(
                "a", "b"
        );
    }

    @Test
    public void mapsOfferedItems() {
        Bid bid = buildBid()
                .withOfferedItems(newHashSet(createItem("a"), createItem("b")))
                .build();
        assertThat(mapper.map(bid, BidDto.class).getOfferedItems()).extracting("id").containsExactlyInAnyOrder(
                "a", "b"
        );
    }

    private Item createItem(String id) {
        Item item = new Item();
        ReflectionTestUtils.setField(item, "id", itemId(id));
        return item;
    }

}
