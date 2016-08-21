package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.TestDataLoader;
import com.kbhit.orangebox.trading.TradingApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class TestUtilsConfig {

    @Bean
    public TestDataLoader testDataLoader() {
        return new TestDataLoader();
    }

}
