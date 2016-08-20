package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.controllers.mapping.BidOutConverter;
import com.kbhit.orangebox.trading.controllers.mapping.ObjectConverter;
import com.kbhit.orangebox.trading.controllers.mapping.TradeOutConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfiguration {

    @Bean
    public ObjectConverter objectConverter() {
        ObjectConverter objectConverter = new ObjectConverter();
        objectConverter.addConverter(new BidOutConverter());
        objectConverter.addConverter(new TradeOutConverter());
        return objectConverter;
    }

}
