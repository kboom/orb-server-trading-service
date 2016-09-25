package com.kbhit.orangebox.trading.controllers.dto.mappers;

import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.controllers.dto.converters.ItemIdConverter;
import com.kbhit.orangebox.trading.domain.TradedItem;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

@Component
public class ItemMapper extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(TradedItem.class, ItemDto.class)
                .fields("itemId", "itemId", customConverter(ItemIdConverter.class));
    }

}