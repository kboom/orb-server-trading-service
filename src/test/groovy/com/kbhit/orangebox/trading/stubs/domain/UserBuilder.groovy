package com.kbhit.orangebox.trading.stubs.domain

import com.kbhit.orangebox.trading.domain.User
import org.springframework.test.util.ReflectionTestUtils

import static com.google.common.collect.Lists.newArrayList

public class UserBuilder {

    private User user = new User();

    UserBuilder withUsername(String username) {
        ReflectionTestUtils.setField(user, "username", username)
        return this
    }

    UserBuilder withPassword(String password) {
        ReflectionTestUtils.setField(user, "password", password)
        return this
    }

    UserBuilder withAuthorities(String... authorities) {
        ReflectionTestUtils.setField(user, "authorities", newArrayList(authorities))
        return this
    }

    UserBuilder withActivated(boolean isActivated) {
        ReflectionTestUtils.setField(user, "activated", isActivated)
        return this
    }

    User build() {
        return user;
    }

    public static UserBuilder aUser() {
        new UserBuilder();
    }
}
