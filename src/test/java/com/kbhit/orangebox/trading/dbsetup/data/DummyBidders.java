package com.kbhit.orangebox.trading.dbsetup.data;

import com.kbhit.orangebox.trading.domain.BidderId;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.kbhit.orangebox.trading.dbsetup.builders.BidderDummyBuilder.aDummyBidder;
import static com.kbhit.orangebox.trading.domain.BidderId.referenceBidder;

public class DummyBidders {

    public static final BidderId GRZEGORZ_BIDDER_ID = referenceBidder("grzegorz");

    public static final BidderId AGATA_BIDDER_ID = referenceBidder("agata");


    private static final Operation AGATA_BIDDER = aDummyBidder()
            .withId(AGATA_BIDDER_ID)
            .withFirstName("Agata")
            .withLastName("Gurgul")
            .withLogin("aaagacia")
            .build();

    private static final Operation GRZEGORZ_BIDDER = aDummyBidder()
            .withId(GRZEGORZ_BIDDER_ID)
            .withFirstName("Grzegorz")
            .withLastName("Gurgul")
            .withLogin("kbhit")
            .build();

    public static Operation getAll() {
        return Operations.sequenceOf(AGATA_BIDDER, GRZEGORZ_BIDDER);
    }

}
