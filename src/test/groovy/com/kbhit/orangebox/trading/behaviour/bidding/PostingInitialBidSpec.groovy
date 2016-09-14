package com.kbhit.orangebox.trading.behaviour.bidding

import com.kbhit.orangebox.trading.behaviour.BehaviourSpecification
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.domain.Bid
import com.kbhit.orangebox.trading.domain.BidderService
import com.kbhit.orangebox.trading.domain.Trade
import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.Item
import com.kbhit.orangebox.trading.domain.service.StorageService
import org.springframework.beans.factory.annotation.Autowired

import static com.kbhit.orangebox.trading.domain.Bid.buildBid
import static com.kbhit.orangebox.trading.feignstubs.TestItem.*
import static com.kbhit.orangebox.trading.stubs.ItemBuilder.buildItem
import static com.kbhit.orangebox.trading.stubs.UserBuilder.buildUser
import static java.util.Collections.singletonList
import static org.assertj.core.api.Assertions.assertThat

class PostingInitialBidSpec extends BehaviourSpecification {

    private Item firstGregItem;
    private Item secondGregItem;
    private Item firstAgathaItem;
    private Item secondAgathaItem;
    private User agatha;
    private User greg;

    @Autowired
    private BiddingService biddingService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private BidderService bidderService;

    @Autowired
    DbSetupTestDataLoader testDataLoader


    def setup() {
        agatha = buildUser().withUsername("agatha").build()
        greg = buildUser().withUsername("greg").build()
        firstAgathaItem = buildItem().withId(AGATHA_FIRST_ITEM_ID.getId()).withOwner(agatha).build()
        secondAgathaItem = buildItem().withId(AGATHA_SECOND_ITEM_ID.getId()).withOwner(agatha).build()
        firstGregItem = buildItem().withId(GREG_FIRST_ITEM_ID.getId()).withOwner(greg).build()
        secondGregItem = buildItem().withId(GREG_SECOND_ITEM_ID.getId()).withOwner(greg).build()

        testDataLoader.createDummyBidders();
    }

    def "Can create a trade from initial bid"() {
        given:
        Bid initialBid = buildBid(bidderService)
                .withBidder(bidderService.getOrCreateBidder(greg))
                .withRequestedItems(singletonList(firstAgathaItem))
                .withOfferedItems(singletonList(firstGregItem))
                .build();

        when:
        Trade trade = biddingService.createTradeFor(initialBid)

        then:
        assertThat(trade).isNotNull();
    }

    def "Trade requester is set to initial bidder"() {
        def initialBidder = bidderService.getOrCreateBidder(greg)
        given:
        Bid initialBid = buildBid(bidderService)
                .withBidder(initialBidder)
                .withRequestedItems(singletonList(firstAgathaItem))
                .withOfferedItems(singletonList(firstGregItem))
                .build()

        when:
        Trade trade = biddingService.createTradeFor(initialBid)

        then:
        assertThat(trade.getRequester()).isEqualTo(initialBidder)
    }

}
