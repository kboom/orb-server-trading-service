package com.kbhit.orangebox.trading.tests.rest

import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.domain.service.Item
import com.kbhit.orangebox.trading.stubs.feign.StorageServiceStubber
import com.kbhit.orangebox.trading.stubs.feign.UserServiceStubber
import org.springframework.beans.factory.annotation.Autowired

public class RestTestDriver {

    @Autowired
    UserServiceStubber userServiceStubber;

    @Autowired
    StorageServiceStubber storageServiceStubber;

    public void thereAreUsers(User... users) {
        users.each { user -> userServiceStubber.stubUser(user) }
        [users.toList(), users.toList()].combinations().each {
            combinationOfUsers -> userServiceStubber.stubUsers(combinationOfUsers as User[])
        }
    }

    public void thereAreItems(Item... items) {
        items.each { item -> storageServiceStubber.stubItems(item) }
        [items.toList(), items.toList()].combinations().each {
            combinationOfItems -> storageServiceStubber.stubItems(combinationOfItems as Item[])
        }
    }

}
