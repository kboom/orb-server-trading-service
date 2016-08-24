package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.domain.service.BiddingService;
import com.kbhit.orangebox.trading.domain.service.DefaultBiddingContextService;
import com.kbhit.orangebox.trading.domain.service.LocalTimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {

    @Bean
    public BiddingService biddingService() {
        return new BiddingService();
    }

    @Bean
    public LocalTimeService timeService() {
        return new LocalTimeService();
    }

    @Bean
    public DefaultBiddingContextService biddingContextService() {
        return new DefaultBiddingContextService();
    }

}
