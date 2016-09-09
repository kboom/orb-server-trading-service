package com.kbhit.orangebox.trading.dbsetup.builders;

import com.kbhit.orangebox.trading.domain.BidderId;
import com.ninja_squad.dbsetup.operation.Operation;

import java.util.TreeMap;

import static com.kbhit.orangebox.trading.dbsetup.tables.BidderTable.*;
import static com.ninja_squad.dbsetup.Operations.insertInto;

public class BidderDummyBuilder {

    private TreeMap<String, Object> orderedValuesMap = new TreeMap<>();

    public BidderDummyBuilder withId(String bidderId) {
        orderedValuesMap.put(BIDDER_ID.getColumnName(), bidderId);
        return this;
    }

    public BidderDummyBuilder withFirstName(String firstName) {
        orderedValuesMap.put(FIRST_NAME.getColumnName(), firstName);
        return this;
    }

    public BidderDummyBuilder withLastName(String lastName) {
        orderedValuesMap.put(LAST_NAME.getColumnName(), lastName);
        return this;
    }

    public BidderDummyBuilder withLogin(String login) {
        orderedValuesMap.put(LOGIN.getColumnName(), login);
        return this;
    }

    public Operation build() {
        return insertInto("BIDDERS")
                .columns(orderedValuesMap.keySet().stream().toArray(String[]::new))
                .values(orderedValuesMap.values().stream().toArray(Object[]::new)).build();
    }

    public static BidderDummyBuilder aDummyBidder() {
        return new BidderDummyBuilder();
    }

}
