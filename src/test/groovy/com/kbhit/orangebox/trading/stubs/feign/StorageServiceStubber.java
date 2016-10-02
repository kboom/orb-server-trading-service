package com.kbhit.orangebox.trading.stubs.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import com.kbhit.orangebox.trading.domain.service.Item;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.String.format;


public class StorageServiceStubber {

    private static final String LEFT_BRACKET_UTF = "%5B";
    private static final String RIGHT_BRACKET_UTF = "%5D";

    @Autowired
    private WireMockServer server;

    @Autowired
    private ObjectMapper mapper;

    public void stubItems(Item... items) {
        server.stubFor(get(getUrlForItems(items))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(mapItemsToBody(items))));
    }

    private String mapItemsToBody(Item[] items) {
        return "[" + Arrays.stream(items).map(item -> {
            try {
                return mapper.writeValueAsString(item);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.joining(",")) + "]";
    }

    private UrlPattern getUrlForItems(Item[] items) {
        String rawUrl = format("/items/[%s]", Arrays.stream(items)
                .map(Item::getId).collect(Collectors.joining("%2C%20")));
        return urlEqualTo(withEncodedBrackets(rawUrl));
    }

    private String withEncodedBrackets(String url) {
        return url.replace("[", LEFT_BRACKET_UTF)
                .replace("]", RIGHT_BRACKET_UTF);
    }

}
