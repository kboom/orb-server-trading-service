package com.kbhit.orangebox.trading.domain.service;

import com.kbhit.orangebox.trading.domain.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("orb-storage")
public interface StorageService {

    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Item> getItemsById(@PathVariable List<String> itemIds);

}
