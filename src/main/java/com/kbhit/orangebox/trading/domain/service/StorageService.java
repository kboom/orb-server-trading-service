package com.kbhit.orangebox.trading.domain.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(serviceId = "orb-storage", url = "http://localhost:5000")
public interface StorageService {

    @RequestMapping(method = RequestMethod.GET, value = "/items/{itemIds}")
    List<Item> getItemsById(@PathVariable("itemIds") List<String> itemIds);

}
