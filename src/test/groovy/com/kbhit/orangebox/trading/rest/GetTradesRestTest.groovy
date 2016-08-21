package com.kbhit.orangebox.trading.rest

import com.kbhit.orangebox.trading.TestDataLoader
import org.springframework.beans.factory.annotation.Autowired

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath

class GetTradesRestTest extends RestTest {

    @Autowired
    TestDataLoader testDataLoader

    def setup() {
        testDataLoader.reloadTestData()
    }

    def "Gets single trade"() {
        given:
        def request = given().contentType("application/json")

        when:
        def response = request.when().get("/trades/1")

        then:
        response.then().body(matchesJsonSchemaInClasspath("trade.json"))
    }

}
