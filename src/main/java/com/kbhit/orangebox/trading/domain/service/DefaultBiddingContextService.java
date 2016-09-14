package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.User;
import com.kbhit.orangebox.trading.domain.repository.BidderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class DefaultBiddingContextService implements BiddingContextService {

    @Autowired
    private UserService userService;

    @Autowired
    private BidderRepository bidderRepository;

    @Override
    public Bidder getBiddingUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails =
                    (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findUserByLogin(userDetails.getUsername());//.orElseThrow(IllegalStateException::new);
            return getOrCreateBidder(user);
        } else {
            throw new IllegalStateException("Not authenticated user cannot be a bidder");
        }
    }

    private Bidder getOrCreateBidder(User user) {
        Optional<Bidder> bidderOptional = bidderRepository.findByLogin(user.getLogin());
        if(bidderOptional.isPresent()) {
            return bidderOptional.get();
        } else {
            Bidder bidder = new Bidder();
            return bidderRepository.save(bidder);
        }
    }

}
