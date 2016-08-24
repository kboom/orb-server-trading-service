package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.domain.service.BiddingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public BiddingService biddingService() {
        return new BiddingService();
    }

}
