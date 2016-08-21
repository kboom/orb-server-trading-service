package com.kbhit.orangebox.trading.mocks;

import com.google.common.collect.Lists;
import com.kbhit.orangebox.trading.domain.Item;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static com.kbhit.orangebox.trading.domain.ItemId.itemId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public enum TestItems {

    AGATA_FIRST_ITEM_ID("agata.first"),
    AGATA_SECOND_ITEM_ID("agata.second"),
    GRZEGORZ_FIRST_ITEM_ID("grzegorz.first"),
    GRZEGORZ_SECOND_ITEM_ID("grzegorz.second");

    private String id;
    private Item item;

    TestItems(String id) {
        this.id = id;
        this.item = mock(Item.class);
        when(item.getId()).thenReturn(itemId(id));
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public static List<String> idsOfItems(TestItems... testItems) {
        return Lists.transform(newArrayList(testItems), TestItems::getId);
    }

}
