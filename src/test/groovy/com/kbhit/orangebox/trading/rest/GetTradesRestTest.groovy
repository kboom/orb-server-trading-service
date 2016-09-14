package com.kbhit.orangebox.trading.rest

import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.security.AuthoritiesConstants
import com.kbhit.orangebox.trading.security.jwt.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static com.google.common.collect.Lists.newArrayList
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static org.hamcrest.core.IsEqual.equalTo

class GetTradesRestTest extends RestTest {

    @Autowired
    DbSetupTestDataLoader testDataLoader

    @Autowired
    TokenProvider tokenProvider

    def "Gets single trade"() {
        given:
        testDataLoader.createDummyBidders();
        testDataLoader.createDummyTrade();
        def token = tokenProvider.createToken(new TestingAuthenticationToken("me", "abc", newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false)
        def request = given().accept("application/json").header("Authorization", "Bearer " + token)

        when:
        def response = request.when().get("/trades/1")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("trade.json"))
                .body("id", equalTo("1"))
                .body("requester.login", equalTo("aaagacia"))
                .body("responder.login", equalTo("kbhit"))
                .body("initialBid.bidder.login", equalTo("aaagacia"))
                .body("initialBid.requestedItems[0].id", equalTo("BLUE GREG ITEM"))
                .body("initialBid.requestedItems[0].name", equalTo("Blue Greg's item"))
                .body("initialBid.offeredItems[0].id", equalTo("RED AGATHA ITEM"))
                .body("initialBid.offeredItems[0].name", equalTo("Red Agatha's item"))
                .body("latestBid.bidder.login", equalTo("kbhit"))
                .body("latestBid.requestedItems[0].id", equalTo("BLUE AGATHA ITEM"))
                .body("latestBid.requestedItems[0].name", equalTo("Blue Agatha's item"))
                .body("latestBid.requestedItems[1].id", equalTo("RED AGATHA ITEM"))
                .body("latestBid.requestedItems[1].name", equalTo("Red Agatha's item"))
                .body("latestBid.offeredItems[0].id", equalTo("BLUE GREG ITEM"))
                .body("latestBid.offeredItems[0].name", equalTo("Blue Greg's item"))
                .body("latestBid.offeredItems[1].id", equalTo("RED GREG ITEM"))
                .body("latestBid.offeredItems[1].name", equalTo("Red Greg's item"))
                .body("historicBids[0].bidder.login", equalTo("aaagacia"))
                .body("historicBids[0].requestedItems[0].id", equalTo("BLUE GREG ITEM"))
                .body("historicBids[0].requestedItems[0].name", equalTo("Blue Greg's item"))
                .body("historicBids[0].offeredItems[0].id", equalTo("RED AGATHA ITEM"))
                .body("historicBids[0].offeredItems[0].name", equalTo("Red Agatha's item"))
                .body("historicBids[1].bidder.login", equalTo("kbhit"))
                .body("historicBids[1].requestedItems[0].id", equalTo("BLUE AGATHA ITEM"))
                .body("historicBids[1].requestedItems[0].name", equalTo("Blue Agatha's item"))
                .body("historicBids[1].requestedItems[1].id", equalTo("RED AGATHA ITEM"))
                .body("historicBids[1].requestedItems[1].name", equalTo("Red Agatha's item"))
                .body("historicBids[1].offeredItems[0].id", equalTo("BLUE GREG ITEM"))
                .body("historicBids[1].offeredItems[0].name", equalTo("Blue Greg's item"))
                .body("historicBids[1].offeredItems[1].id", equalTo("RED GREG ITEM"))
                .body("historicBids[1].offeredItems[1].name", equalTo("Red Greg's item"))

    }

}
