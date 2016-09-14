package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.feignstubs.ItemServiceStubber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StandaloneConfig {

    @Bean
    public ItemServiceStubber itemServiceStubber() {
        return new ItemServiceStubber();
    }

}
