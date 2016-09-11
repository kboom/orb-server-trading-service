package com.kbhit.orangebox.trading.dbsetup.mapping;

import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.domain.Item;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemMappingTests extends MappingTest {

    @Autowired
    private Mapper mapper;

    @Test
    public void mapsItemProperties() {
        Item item = new Item();
        assertThat(mapper.map(item, ItemDto.class)).isEqualToComparingFieldByField(new ItemDto());
    }

}
