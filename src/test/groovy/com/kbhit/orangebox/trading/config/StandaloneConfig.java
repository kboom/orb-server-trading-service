package com.kbhit.orangebox.trading.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kbhit.orangebox.trading.stubs.feign.StorageServiceStubber;
import com.kbhit.orangebox.trading.stubs.feign.UserServiceStubber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@Configuration
public class StandaloneConfig {

    @Bean
    public WireMockServer wireMockServer() {
        return new WireMockServer(wireMockConfig().port(5000));
    }

    @Bean
    public StorageServiceStubber itemServiceStubber() {
        return new StorageServiceStubber();
    }

    @Bean
    public UserServiceStubber userServiceStubber() {
        return new UserServiceStubber();
    }

}
