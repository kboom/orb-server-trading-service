package com.kbhit.orangebox.trading.behaviour.bidding

import com.kbhit.orangebox.trading.behaviour.BehaviourSpecification
import com.kbhit.orangebox.trading.domain.Bid
import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.StorageService
import org.springframework.beans.factory.annotation.Autowired

import static com.kbhit.orangebox.trading.domain.Bid.buildBid
import static com.kbhit.orangebox.trading.mocks.TestItem.*
import static org.assertj.core.api.Assertions.assertThat

class PostingInitialBidSpec extends BehaviourSpecification {

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private StorageService storageService;


    def "Can create trade using initial bid"() {
        given:
        Bid initialBid = buildBid()
                .withRequestedItems(storageService.getItemsById(idsOfItems(AGATA_FIRST_ITEM_ID, AGATA_SECOND_ITEM_ID)))
                .withOfferedItems(storageService.getItemsById(idsOfItems(GRZEGORZ_FIRST_ITEM_ID)))
                .build();

        when:
        Trade trade = biddingService.createTradeFor(initialBid)

        then:
        assertThat(trade.isActive()).isTrue();
    }

}
