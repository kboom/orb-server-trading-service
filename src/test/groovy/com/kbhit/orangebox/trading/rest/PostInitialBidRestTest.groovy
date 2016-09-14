package com.kbhit.orangebox.trading.rest

import com.jayway.restassured.http.ContentType
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.feignstubs.UserServiceStubber
import com.kbhit.orangebox.trading.security.AuthoritiesConstants
import com.kbhit.orangebox.trading.security.jwt.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static com.google.common.collect.Lists.newArrayList
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static com.kbhit.orangebox.trading.stubs.UserBuilder.buildUser
import static org.hamcrest.core.IsEqual.equalTo

class PostInitialBidRestTest extends RestTest {

    @Autowired
    DbSetupTestDataLoader testDataLoader

    @Autowired
    UserServiceStubber userServiceStubber;

    @Autowired
    TokenProvider tokenProvider

    def "Posting initial bid creates a new trade"() {
        given:
        userServiceStubber.stubUser(buildUser()
                .byDefault()
                .withUsername("greg")
                .withPassword("123")
                .build());

        def token = tokenProvider.createToken(new TestingAuthenticationToken("greg", "123", newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false)
        def request = given()
                .contentType(ContentType.JSON)
                .body('{ "requestedItems" : [{ "id" : "a" }], "offeredItems": [{ "id" : "b" }] }')
                .header("Authorization", "Bearer " + token)

        when:
        def response = request.when().post("/bids")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("trade.json"))
                .body("id", equalTo("1"));

    }

}
