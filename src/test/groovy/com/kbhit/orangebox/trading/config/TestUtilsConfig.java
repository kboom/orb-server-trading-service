package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.TestDataLoader;
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestUtilsConfig {

    @Bean
    public TestDataLoader testDataLoader() {
        return new DbSetupTestDataLoader();
    }

}
