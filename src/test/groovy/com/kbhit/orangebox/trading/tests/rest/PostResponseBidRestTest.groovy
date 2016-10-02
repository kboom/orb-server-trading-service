package com.kbhit.orangebox.trading.tests.rest

import com.jayway.restassured.http.ContentType
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.stubs.feign.StorageServiceStubber
import com.kbhit.orangebox.trading.stubs.feign.UserServiceStubber
import com.kbhit.orangebox.trading.security.AuthoritiesConstants
import com.kbhit.orangebox.trading.security.jwt.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static com.google.common.collect.Lists.newArrayList
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static com.kbhit.orangebox.trading.stubs.domain.ItemBuilder.buildItem
import static com.kbhit.orangebox.trading.stubs.domain.UserBuilder.buildUser

class PostResponseBidRestTest extends RestTest {

    @Autowired
    DbSetupTestDataLoader testDataLoader

    @Autowired
    UserServiceStubber userServiceStubber;

    @Autowired
    StorageServiceStubber storageServiceStubber;

    @Autowired
    TokenProvider tokenProvider

    def "Posting response bid changes existing trade"() {
        def gregUser = buildUser()
                .byDefault()
                .withUsername("greg")
                .withPassword("123")
                .build()

        def agathaUser = buildUser()
                .byDefault()
                .withUsername("agatha")
                .withPassword("123")
                .build()

        given:
        userServiceStubber.stubUser(gregUser);

        storageServiceStubber.stubItems(
                buildItem()
                        .withId("a")
                        .withOwner(agathaUser)
                        .withName("item a")
                        .build()
        )

        storageServiceStubber.stubItems(
                buildItem()
                        .withId("b")
                        .withOwner(gregUser)
                        .withName("item b")
                        .build()
        )

        testDataLoader.createDummyBidders();
        testDataLoader.createDummyTrade();

        def token = tokenProvider.createToken(new TestingAuthenticationToken("greg", "123", newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false)
        def request = given()
                .pathParam("tradeId", "1")
                .contentType(ContentType.JSON)
                .body('{ "requestedItems" : [{ "itemId" : "a" }], "offeredItems": [{ "itemId" : "b" }] }')
                .header("Authorization", "Bearer " + token)

        when:
        def response = request.when().post("/trades/{tradeId}/bids")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("latestBid.json"));
    }



}
