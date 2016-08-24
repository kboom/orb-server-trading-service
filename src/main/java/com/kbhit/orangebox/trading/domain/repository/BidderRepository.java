package com.kbhit.orangebox.trading.domain.repository;

import com.kbhit.orangebox.trading.domain.Bidder;
import com.kbhit.orangebox.trading.domain.BidderId;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface BidderRepository extends Repository<Bidder, BidderId> {

    Optional<Bidder> findByLogin(String login);

    Bidder save(Bidder bidder);
}
