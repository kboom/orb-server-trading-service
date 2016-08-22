package com.kbhit.orangebox.trading.rest

import com.jayway.restassured.RestAssured
import com.kbhit.orangebox.trading.TradingApplication
import com.kbhit.orangebox.trading.config.TestUtilsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles("dev")
@SpringBootTest(classes = [TradingApplication.class, TestUtilsConfig.class],
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class RestTest extends Specification {

    @Value('${local.server.port}')
    int port

    def setup() {
        RestAssured.port = port
    }

}
