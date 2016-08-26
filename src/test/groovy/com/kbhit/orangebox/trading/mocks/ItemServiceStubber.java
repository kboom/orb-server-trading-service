package com.kbhit.orangebox.trading.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.kbhit.orangebox.trading.controllers.dto.ItemDto;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


public class ItemServiceStubber {

    private WireMockServer server = new WireMockServer(wireMockConfig().port(5000));

    public void mockItem(ItemDto itemDto) {
        stubFor(get(urlEqualTo("/items/")).willReturn(aResponse().withStatus(200).withBody("abc")));
    }

    public void start() {
        server.start();
        WireMock.configureFor(5000);
    }
}
