package com.kbhit.orangebox.trading.domain.repository;

import com.kbhit.orangebox.trading.domain.Bid;
import org.springframework.data.repository.Repository;

public interface BidRepository extends Repository<Bid, Integer> {

    Bid save(Bid bid);

}
