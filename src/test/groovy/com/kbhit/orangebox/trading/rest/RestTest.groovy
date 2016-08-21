package com.kbhit.orangebox.trading.rest

import com.jayway.restassured.RestAssured
import com.kbhit.orangebox.trading.TradingApplication
import com.kbhit.orangebox.trading.config.TestUtilsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringApplicationConfiguration(classes = [ TradingApplication.class, TestUtilsConfig.class ])
@ActiveProfiles("dev")
@WebIntegrationTest("server.port:0")
abstract class RestTest extends Specification {

    @Value('${local.server.port}')
    int port

    def setup() {
        RestAssured.port = port
    }

}