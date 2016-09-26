package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.stubs.ConfigurableTimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class TestServicesConfig {

    @Bean
    @Primary
    public ConfigurableTimeService timeService() {
        return new ConfigurableTimeService();
    }

}
