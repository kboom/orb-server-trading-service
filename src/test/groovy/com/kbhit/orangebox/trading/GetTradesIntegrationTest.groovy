package com.kbhit.orangebox.trading

import org.springframework.beans.factory.annotation.Autowired

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath

class GetTradesIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    TestDataLoader testDataLoader

    def setup() {
        testDataLoader.reloadTestData()
    }

    def "Gets api"() {
        given:
        def request = given()
                .contentType("application/json")
        when:
        def response = request.when().get("/api")

        then:
        response.then().statusCode(200);
    }

    def "Gets api - resource list"() {
        given:
        def request = given()
                .contentType("application/json")
        when:
        def response = request.when().get("/api")

        then:
        response.then().body(matchesJsonSchemaInClasspath("api.json"))
    }

}