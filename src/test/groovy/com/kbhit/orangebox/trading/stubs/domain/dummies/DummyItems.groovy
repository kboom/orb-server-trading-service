package com.kbhit.orangebox.trading.stubs.domain.dummies

import com.kbhit.orangebox.trading.domain.Bidder
import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.domain.service.Item
import org.springframework.stereotype.Component

import static com.kbhit.orangebox.trading.stubs.domain.ItemBuilder.anItem
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.getUserForBidder

@Component
class DummyItems {

    static Collection<Item> someItemsBelongingTo(Bidder bidder) {
        return [1..3].collect { i -> dummyItem(getUserForBidder(bidder), String.valueOf(i)) }
    }

    static Item[] someItemsBelongingTo(User user) {
        return [1..3].collect { i -> dummyItem(user, String.valueOf(i)) }
    }

    static Item dummyItem(User owner, String id) {
        return anItem()
                .withId(id)
                .withOwner(owner)
                .withName("item $id")
                .build();
    }

}
