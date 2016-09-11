package com.kbhit.orangebox.trading.controllers.dto.mappers;

import com.kbhit.orangebox.trading.controllers.dto.TradeDto;
import com.kbhit.orangebox.trading.controllers.dto.converters.TradeIdConverter;
import com.kbhit.orangebox.trading.domain.Trade;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.stereotype.Component;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;

@Component
public class TradeMapper extends BeanMappingBuilder {

    @Override
    protected void configure() {
        mapping(Trade.class, TradeDto.class, TypeMappingOptions.oneWay())
                .fields("id", "id", customConverter(TradeIdConverter.class));
    }

}
