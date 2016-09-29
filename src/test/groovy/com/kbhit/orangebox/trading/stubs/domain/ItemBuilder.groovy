package com.kbhit.orangebox.trading.stubs.domain

import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.domain.service.Item
import org.springframework.test.util.ReflectionTestUtils

public class ItemBuilder {

    private Item item = new Item();

    public ItemBuilder withId(String id) {
        ReflectionTestUtils.setField(item, "id", id);
        return this;
    }

    public ItemBuilder withOwner(User user) {
        ReflectionTestUtils.setField(item, "owner", user);
        return this;
    }

    public ItemBuilder withName(String name) {
        ReflectionTestUtils.setField(item, "name", name);
        return this;
    }

    public Item build() {
        return item;
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

}
