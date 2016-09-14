package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.TestDataLoader;
import com.kbhit.orangebox.trading.domain.repository.BidderRepository;
import com.kbhit.orangebox.trading.stubs.DummyUsers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kbhit.orangebox.trading.domain.BidderId.referenceBidder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BidderServiceTest extends DomainTest {

    @Autowired
    private BidderService bidderService;

    @Autowired
    private BidderRepository bidderRepository;

    @Test
    public void persistsNewBidderIfNotPresent() {
        bidderService.getOrCreateBidder(DummyUsers.otherUser);
        assertThat(bidderRepository.findByLogin(DummyUsers.otherUser.getLogin())).isNotNull();
    }

    @Test
    public void doesNotCreateNewBidderIfAlreadyPresent() {
        bidderService.getOrCreateBidder(DummyUsers.gregUser);
    }

    @Test
    public void returnsNewlyCreatedBidder() {
        Bidder createdBidder = bidderService.getOrCreateBidder(DummyUsers.otherUser);
        assertThat(createdBidder.getId()).isEqualTo(referenceBidder(DummyUsers.otherUser.getLogin()));
    }

    @Test
    public void newlyCreatedBidderHasLoginLikeUser() {
        Bidder createdBidder = bidderService.getOrCreateBidder(DummyUsers.otherUser);
        assertThat(createdBidder.getLogin()).isEqualTo(DummyUsers.otherUser.getLogin());
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
    }

}