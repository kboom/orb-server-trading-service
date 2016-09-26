package com.kbhit.orangebox.trading.stubs.domain.dummies;

import com.kbhit.orangebox.trading.domain.User;

import static com.kbhit.orangebox.trading.stubs.domain.UserBuilder.buildUser;

public class Actors {

    public final User agatha;
    public final User greg;

    public Actors() {
        agatha = buildUser().withUsername("agatha").build();
        greg = buildUser().withUsername("greg").build();
    }

}
