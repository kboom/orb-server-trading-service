package com.kbhit.orangebox.trading;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(TradingApplication.class)
public class IntegrationTestContext {

    @Bean
    public TestDataLoader testDataLoader() {
        return new TestDataLoader();
    }

}
