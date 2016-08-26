package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.mocks.ItemServiceStubber;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandaloneConfig {

    @Bean
    public ItemServiceStubber itemServiceStubber() {
        return new ItemServiceStubber();
    }

}
