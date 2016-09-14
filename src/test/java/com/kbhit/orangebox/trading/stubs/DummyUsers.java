package com.kbhit.orangebox.trading.stubs;

import com.kbhit.orangebox.trading.domain.User;

import static com.kbhit.orangebox.trading.stubs.UserBuilder.buildUser;

public class DummyUsers {

    public static final User gregUser = buildUser().byDefault().withUsername("greg").build();

    public static final User agathaUser = buildUser().byDefault().withUsername("agatha").build();

    public static final User otherUser = buildUser().byDefault().withUsername("other").build();

}
