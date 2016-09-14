package com.kbhit.orangebox.trading.stubs;

import com.kbhit.orangebox.trading.domain.Authority;
import com.kbhit.orangebox.trading.domain.User;
import org.springframework.test.util.ReflectionTestUtils;

import static com.google.common.collect.Lists.newArrayList;

public class UserBuilder {

    private User user = new User();

    private UserBuilder() {
    }

    public UserBuilder withUsername(String username) {
        ReflectionTestUtils.setField(user, "username", username);
        return this;
    }

    public UserBuilder withPassword(String password) {
        ReflectionTestUtils.setField(user, "password", password);
        return this;
    }

    public UserBuilder withActivated(boolean activated) {
        ReflectionTestUtils.setField(user, "activated", activated);
        return this;
    }

    public UserBuilder withAuthorities(Authority... authorities) {
        ReflectionTestUtils.setField(user, "authorities", newArrayList(authorities));
        return this;
    }

    public UserBuilder byDefault() {
        return withActivated(true)
                .withAuthorities(new Authority("bidder"));
    }

    public User build() {
        return user;
    }

    public static UserBuilder buildUser() {
        return new UserBuilder();
    }
}
