package com.kbhit.orangebox.trading.feignstubs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.kbhit.orangebox.trading.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.lang.String.format;

public class UserServiceStubber {

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

}
