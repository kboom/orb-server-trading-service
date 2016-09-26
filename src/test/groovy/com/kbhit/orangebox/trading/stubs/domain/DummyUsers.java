package com.kbhit.orangebox.trading.stubs.domain;

import com.kbhit.orangebox.trading.domain.User;

public class DummyUsers {

    public static final User gregUser = UserBuilder.buildUser().byDefault().withUsername("greg").build();

    public static final User agathaUser = UserBuilder.buildUser().byDefault().withUsername("agatha").build();

    public static final User otherUser = UserBuilder.buildUser().byDefault().withUsername("other").build();

}
