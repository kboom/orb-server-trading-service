package com.kbhit.orangebox.trading.tests.behaviour

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.domain.Bidder
import com.kbhit.orangebox.trading.domain.BidderService
import com.kbhit.orangebox.trading.domain.CounterParties
import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.repository.BidderRepository
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.StorageService
import com.kbhit.orangebox.trading.stubs.ConfigurableTimeService
import org.springframework.beans.factory.annotation.Autowired

import static org.assertj.core.api.Assertions.assertThat

class TradingSpec extends BehaviourSpecification {

    @Autowired
    BiddingService biddingService;

    @Autowired
    StorageService storageService;

    @Autowired
    BidderService bidderService;

    @Autowired
    ConfigurableTimeService timeService;

    @Autowired
    BidderRepository bidderRepository;

    def "Can create a trade between two bidders"() {
        given:
        final Bidder requester = bidderRepository.findByLogin("agatha").get()
        final Bidder responder = bidderRepository.findByLogin("greg").get()
        CounterParties counterParties = new CounterParties(requester, responder)

        when:
        Trade trade = biddingService.createTradeBetween(counterParties)

        then:
        assertThat(trade).isNotNull();
        assertThat(trade.getRequester()).isEqualTo(requester)
        assertThat(trade.getResponder()).isEqualTo(responder)
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
    }

}
