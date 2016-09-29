package com.kbhit.orangebox.trading.stubs.domain.dummies

import com.kbhit.orangebox.trading.domain.User
import org.junit.Test

import static org.assertj.core.api.AssertionsForClassTypes.assertThat


class GroovyTest {

    @Test
    public void canDo() {
        User agatha = new User().with {
            username: 'agatha'
        }
        assertThat(agatha.getUsername()).isEqualTo("agatha")
    }

}
