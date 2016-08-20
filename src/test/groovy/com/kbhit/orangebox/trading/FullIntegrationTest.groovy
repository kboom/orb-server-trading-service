package com.kbhit.orangebox.trading

import com.jayway.restassured.RestAssured
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringApplicationConfiguration(classes = IntegrationTestContext.class)
@ActiveProfiles("dev")
@WebIntegrationTest("server.port:0")
abstract class FullIntegrationTest extends Specification {

    @Value('${local.server.port}')
    int port

    def setup() {
        RestAssured.port = port
    }

}
