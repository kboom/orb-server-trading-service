package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.domain.service.StorageService;
import com.kbhit.orangebox.trading.mocks.MockedStorageService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class StandaloneConfig {

    @Bean
    public StorageService storageService() {
        return new MockedStorageService();
    }

}
