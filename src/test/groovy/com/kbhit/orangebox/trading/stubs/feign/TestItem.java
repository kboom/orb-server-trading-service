package com.kbhit.orangebox.trading.stubs.feign;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kbhit.orangebox.trading.domain.service.Item;

import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import static com.kbhit.orangebox.trading.domain.ItemId.itemId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public enum TestItem {

    AGATHA_FIRST_ITEM_ID("agata.first"),
    AGATHA_SECOND_ITEM_ID("agata.second"),
    GREG_FIRST_ITEM_ID("grzegorz.first"),
    GREG_SECOND_ITEM_ID("grzegorz.second");

    private String id;
    private Item item;

    TestItem(String id) {
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

    public static List<String> idsOfItems(TestItem... testItems) {
        return Lists.transform(newArrayList(testItems), TestItem::getId);
    }

    public static Map<String, TestItem> allAsMap() {
        return Maps.uniqueIndex(newHashSet(values()), TestItem::getId);
    }

}
