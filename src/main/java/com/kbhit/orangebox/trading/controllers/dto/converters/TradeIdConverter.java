package com.kbhit.orangebox.trading.controllers.dto.converters;

import com.kbhit.orangebox.trading.domain.TradeId;
import org.dozer.CustomConverter;
import org.springframework.stereotype.Component;

@Component
public class TradeIdConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        return sourceFieldValue != null ? ((TradeId) sourceFieldValue).rawValue() : null;
    }

}
