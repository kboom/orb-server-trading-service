package com.kbhit.orangebox.trading.domain;

import com.kbhit.orangebox.trading.domain.repository.BidderRepository;
import com.kbhit.orangebox.trading.domain.service.Item;
import com.kbhit.orangebox.trading.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

public class BidderService {

    @Autowired
    private BidderRepository bidderRepository;

    @Autowired
    private UserService userService;


    public Bidder getOwnerOf(Collection<Item> items) {
        Item item = items.iterator().next(); // todo for now don't validate for a single owner
        return getOrCreateBidder(item.getOwner());
    }

    public Bidder getOrCreateBidder(User user) {
        Optional<Bidder> bidderOptional = bidderRepository.findByLogin(user.getUsername());
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

    public CounterParties resolveCounterParties(String requesterLogin, String responderLogin) {
        List<User> users = userService.findUserByLogin(newArrayList(requesterLogin, responderLogin));
        Bidder requester = getOrCreateBidder(users.get(0));
        Bidder responder = getOrCreateBidder(users.get(1));
        return new CounterParties(requester, responder);
    }
}
