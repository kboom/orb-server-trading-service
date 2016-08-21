package com.kbhit.orangebox.trading.mocks;

import com.google.common.collect.Lists;
import com.kbhit.orangebox.trading.domain.Item;
import com.kbhit.orangebox.trading.domain.service.StorageService;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

public class MockedStorageService implements StorageService {

    private Map<String, Item> itemMap;

    @Override
    public List<Item> getItemsById(@PathVariable List<String> itemIds) {
        return Lists.transform(itemIds, id -> itemMap.get(id));
    }

}
