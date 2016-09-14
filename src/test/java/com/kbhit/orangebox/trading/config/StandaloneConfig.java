package com.kbhit.orangebox.trading.config;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kbhit.orangebox.trading.feignstubs.ItemServiceStubber;
import com.kbhit.orangebox.trading.feignstubs.UserServiceStubber;
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
    public ItemServiceStubber itemServiceStubber() {
        return new ItemServiceStubber();
    }

    @Bean
    public UserServiceStubber userServiceStubber() {
        return new UserServiceStubber();
    }

}
