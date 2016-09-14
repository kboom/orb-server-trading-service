package com.kbhit.orangebox.trading.feignstubs;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.kbhit.orangebox.trading.controllers.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


public class ItemServiceStubber {

    @Autowired
    private WireMockServer server;

    public void mockItem(ItemDto itemDto) {
        server.stubFor(get(urlEqualTo("/items/")).willReturn(aResponse().withStatus(200).withBody("abc")));
    }

}
