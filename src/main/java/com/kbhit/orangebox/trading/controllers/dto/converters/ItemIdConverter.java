package com.kbhit.orangebox.trading.controllers.dto.converters;

import com.kbhit.orangebox.trading.domain.ItemId;
import org.dozer.CustomConverter;
import org.springframework.stereotype.Component;

@Component
public class ItemIdConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        return sourceFieldValue != null ? ((ItemId) sourceFieldValue).rawValue() : null;
    }

}