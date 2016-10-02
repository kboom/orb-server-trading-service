package com.kbhit.orangebox.trading.stubs.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.UrlPattern;
import com.kbhit.orangebox.trading.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.String.format;

public class UserServiceStubber {

    private static final String LEFT_BRACKET_UTF = "%5B";
    private static final String RIGHT_BRACKET_UTF = "%5D";

    @Autowired
    WireMockServer server;

    @Autowired
    ObjectMapper mapper;

    public void stubUser(User user) {
        try {
            server.stubFor(get(urlEqualTo(format("/users/%s", user.getUsername())))
                    .willReturn(aResponse().withHeader("Content-Type", "application/json").withStatus(200).withBody(mapper.writeValueAsString(user))));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public void stubUsers(User... users) {
        server.stubFor(get(getUrlForUsers(users))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBody(mapUsersToBody(users))));
    }

    private String mapUsersToBody(User[] users) {
        return "[" + Arrays.stream(users).map(item -> {
            try {
                return mapper.writeValueAsString(item);
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).collect(Collectors.joining(",")) + "]";
    }

    private UrlPattern getUrlForUsers(User[] users) {
        String rawUrl = format("/users/[%s]", Arrays.stream(users)
                .map(User::getUsername).collect(Collectors.joining("%2C%20")));
        return urlEqualTo(withEncodedBrackets(rawUrl));
    }

    private String withEncodedBrackets(String url) {
        return url.replace("[", LEFT_BRACKET_UTF)
                .replace("]", RIGHT_BRACKET_UTF);
    }

}
