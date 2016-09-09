package com.kbhit.orangebox.trading.dbsetup.data;

import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.operation.Operation;

import static com.kbhit.orangebox.trading.dbsetup.builders.BidderDummyBuilder.aDummyBidder;

public class DummyBidders {

    static final String GRZEGORZ_BIDDER_ID = "grzegorz";

    static final String AGATA_BIDDER_ID = "agata";


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
