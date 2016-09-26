package com.kbhit.orangebox.trading.stubs.domain.dummies;

import com.kbhit.orangebox.trading.domain.User;
import com.kbhit.orangebox.trading.stubs.domain.ItemBuilder;

public class DummyItems {


//    public final Item firstGregItem;
//    public final Item secondGregItem;
//    public final Item firstAgathaItem;
//    public final Item secondAgathaItem;
//
//    public DummyItems(Actors actors) {
//        firstAgathaItem = buildItem().withId(AGATHA_FIRST_ITEM_ID.getId()).withOwner(actors.agatha).build();
//        secondAgathaItem = buildItem().withId(AGATHA_SECOND_ITEM_ID.getId()).withOwner(actors.agatha).build();
//        firstGregItem = buildItem().withId(GREG_FIRST_ITEM_ID.getId()).withOwner(actors.greg).build();
//        secondGregItem = buildItem().withId(GREG_SECOND_ITEM_ID.getId()).withOwner(actors.greg).build();
//    }

    public ItemBuilder dummyItemOwnedBy(User user) {
        ItemBuilder itemBuilder = new ItemBuilder();
        itemBuilder.withId("123");
        itemBuilder.withName("dummy item");
        itemBuilder.withOwner(user);
        return itemBuilder;
    }

}
