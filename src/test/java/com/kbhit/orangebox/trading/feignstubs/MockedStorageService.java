package com.kbhit.orangebox.trading.feignstubs;

import com.google.common.collect.Lists;
import com.kbhit.orangebox.trading.domain.service.Item;
import com.kbhit.orangebox.trading.domain.service.StorageService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public class MockedStorageService implements StorageService {

    private Map<String, TestItem> itemMap = TestItem.allAsMap();

    @Override
    public List<Item> getItemsById(@PathVariable List<String> itemIds) {
        return unwrapItems(getItemsByIds(itemIds));
    }

    private List<TestItem> getItemsByIds(@PathVariable List<String> itemIds) {
        return Lists.transform(itemIds, id -> itemMap.get(id));
    }

    private List<Item> unwrapItems(List<TestItem> items) {
        return Lists.transform(items, TestItem::getItem);
    }

}
