package com.kbhit.orangebox.trading.controllers.dto.mappers;

import com.kbhit.orangebox.trading.controllers.dto.BidDto;
import com.kbhit.orangebox.trading.controllers.dto.converters.TradeIdConverter;
import com.kbhit.orangebox.trading.domain.Bid;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.stereotype.Component;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

@Component
public class BidMapper extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Bid.class, BidDto.class)
                .exclude("requestedItems")
                .exclude("offeredItems")
                .fields("requestedItemsSorted", "requestedItems")
                .fields("offeredItemsSorted", "offeredItems");
    }

}