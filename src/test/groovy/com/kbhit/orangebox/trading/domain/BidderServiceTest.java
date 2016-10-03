package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.TestDataLoader;
import com.kbhit.orangebox.trading.domain.repository.BidderRepository;
import com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.kbhit.orangebox.trading.domain.BidderId.referenceBidder;
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.gregUser;
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.otherUser;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BidderServiceTest extends DomainTest {

    private static final User otherUser = otherUser().build();
    private static final User gregUser = gregUser().build();

    @Autowired
    private BidderService bidderService;

    @Autowired
    private BidderRepository bidderRepository;

    @Test
    public void persistsNewBidderIfNotPresent() {
        bidderService.getOrCreateBidder(otherUser);
        assertThat(bidderRepository.findByLogin(otherUser.getUsername())).isNotNull();
    }

    @Test
    public void doesNotCreateNewBidderIfAlreadyPresent() {
        bidderService.getOrCreateBidder(gregUser);
    }

    @Test
    public void returnsNewlyCreatedBidder() {
        Bidder createdBidder = bidderService.getOrCreateBidder(otherUser);
        assertThat(createdBidder.getId()).isEqualTo(referenceBidder(otherUser.getUsername()));
    }

    @Test
    public void newlyCreatedBidderHasLoginLikeUser() {
        Bidder createdBidder = bidderService.getOrCreateBidder(otherUser);
        assertThat(createdBidder.getLogin()).isEqualTo(otherUser.getUsername());
    }

    @Override
    protected void loadTestData(TestDataLoader testDataLoader) {
        testDataLoader.createDummyBidders();
    }

}