package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.repository.BidderRepository;
import com.kbhit.orangebox.trading.domain.service.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Optional;

public class BidderService {

    @Autowired
    private BidderRepository bidderRepository;


    public Bidder getOwnerOf(Collection<Item> items) {
        Item item = items.iterator().next(); // todo for now don't validate for a single owner
        return getOrCreateBidder(item.getOwner());
    }

    public Bidder getOrCreateBidder(User user) {
        Optional<Bidder> bidderOptional = bidderRepository.findByLogin(user.getLogin());
        if (bidderOptional.isPresent()) {
            return bidderOptional.get();
        } else {
            Bidder bidder = createNewBidder(user);
            return bidderRepository.save(bidder);
        }
    }

    private Bidder createNewBidder(User user) {
        return new Bidder(user);
    }

}
