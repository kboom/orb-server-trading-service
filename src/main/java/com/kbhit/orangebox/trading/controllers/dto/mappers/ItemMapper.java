package com.kbhit.orangebox.trading.controllers.dto.mappers;

import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import com.kbhit.orangebox.trading.controllers.dto.converters.ItemIdConverter;
import com.kbhit.orangebox.trading.domain.BidItem;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

@Component
public class ItemMapper extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(BidItem.class, ItemDto.class)
                .fields("id", "id", customConverter(ItemIdConverter.class));
    }

}