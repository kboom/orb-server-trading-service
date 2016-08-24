package com.kbhit.orangebox.trading.rest

import com.kbhit.orangebox.trading.TestDataLoader
import com.kbhit.orangebox.trading.security.AuthoritiesConstants
import com.kbhit.orangebox.trading.security.jwt.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static com.google.common.collect.Lists.newArrayList
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath

class GetTradesRestTest extends RestTest {

    @Autowired
    TestDataLoader testDataLoader

    @Autowired
    TokenProvider tokenProvider

    def setup() {
        testDataLoader.reloadTestData()
    }

    def "Gets single trade"() {
        given:
        def token = tokenProvider.createToken(new TestingAuthenticationToken("me", "abc", newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false)
        def request = given().accept("application/json").header("Authorization", "Bearer " + token)

        when:
        def response = request.when().get("/trades/1")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("trade.json"))
    }

}
