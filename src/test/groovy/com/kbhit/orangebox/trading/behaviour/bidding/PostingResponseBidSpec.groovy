package com.kbhit.orangebox.trading.behaviour.bidding

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.behaviour.BehaviourSpecification
import com.kbhit.orangebox.trading.dbsetup.data.InsertOngoingTrade
import com.kbhit.orangebox.trading.domain.*
import com.kbhit.orangebox.trading.domain.service.BiddingService
import com.kbhit.orangebox.trading.domain.service.Item
import com.kbhit.orangebox.trading.domain.service.StorageService
import com.kbhit.orangebox.trading.stubs.ConfigurableTimeService
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Shared

import static com.kbhit.orangebox.trading.domain.Bid.buildBid
import static com.kbhit.orangebox.trading.feignstubs.TestItem.*
import static com.kbhit.orangebox.trading.stubs.ItemBuilder.buildItem
import static com.kbhit.orangebox.trading.stubs.UserBuilder.buildUser
import static java.util.Collections.singletonList
import static org.assertj.core.api.Assertions.assertThat

public class PostingResponseBidSpec extends BehaviourSpecification {

    @Shared
    Item firstGregItem;
    @Shared
    Item secondGregItem;
    @Shared
    Item firstAgathaItem;
    @Shared
    Item secondAgathaItem;
    @Shared
    User agatha;
    @Shared
    User greg;

    @Autowired
    BiddingService biddingService;

    @Autowired
    StorageService storageService;

    @Autowired
    BidderService bidderService;

    @Autowired
    ConfigurableTimeService timeService;

    def setupSpec() {
        agatha = buildUser().withUsername("agatha").build()
        greg = buildUser().withUsername("greg").build()
        firstAgathaItem = buildItem().withId(AGATHA_FIRST_ITEM_ID.getId()).withOwner(agatha).build()
        secondAgathaItem = buildItem().withId(AGATHA_SECOND_ITEM_ID.getId()).withOwner(agatha).build()
        firstGregItem = buildItem().withId(GREG_FIRST_ITEM_ID.getId()).withOwner(greg).build()
        secondGregItem = buildItem().withId(GREG_SECOND_ITEM_ID.getId()).withOwner(greg).build()
    }

    def "Posted bid becomes latest in trade it is posted to"() {
        given:
        Bid responseBid = buildBid(bidderService)
                .withBidder(bidderService.getOrCreateBidder(greg))
                .withRequestedItems(singletonList(firstAgathaItem))
                .withOfferedItems(singletonList(firstGregItem))
                .build();

        when:
        Trade trade = biddingService.postBidFor(TradeId.referenceTrade(InsertOngoingTrade.ONGOING_TRADE_ID), responseBid)

        then:
        assertThat(trade.getLatestBid()).isEqualTo(responseBid);
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
        testDataLoader.createDummyTrade();
    }

}
