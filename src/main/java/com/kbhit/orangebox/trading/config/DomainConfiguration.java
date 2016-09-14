package com.kbhit.orangebox.trading.config;

import com.kbhit.orangebox.trading.domain.BidderService;
import com.kbhit.orangebox.trading.domain.factory.TradeFactory;
import com.kbhit.orangebox.trading.domain.factory.TradeIdGenerator;
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
    public BidderService bidderService() {
        return new BidderService();
    }

    @Bean
    public LocalTimeService timeService() {
        return new LocalTimeService();
    }

    @Bean
    public DefaultBiddingContextService biddingContextService() {
        return new DefaultBiddingContextService();
    }

    @Bean
    public TradeIdGenerator tradeIdGenerator() {
        return new TradeIdGenerator();
    }

    @Bean
    public TradeFactory tradeFactory() {
        return new TradeFactory();
    }

}
