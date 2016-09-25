package com.kbhit.orangebox.trading.rest

import com.jayway.restassured.http.ContentType
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.feignstubs.StorageServiceStubber
import com.kbhit.orangebox.trading.feignstubs.UserServiceStubber
import com.kbhit.orangebox.trading.security.AuthoritiesConstants
import com.kbhit.orangebox.trading.security.jwt.TokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority

import static com.google.common.collect.Lists.newArrayList
import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static com.kbhit.orangebox.trading.stubs.ItemBuilder.buildItem
import static com.kbhit.orangebox.trading.stubs.UserBuilder.buildUser
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.core.IsEqual.equalTo

class PostInitialBidRestTest extends RestTest {

    @Autowired
    DbSetupTestDataLoader testDataLoader

    @Autowired
    UserServiceStubber userServiceStubber;

    @Autowired
    StorageServiceStubber storageServiceStubber;

    @Autowired
    TokenProvider tokenProvider

    def "Posting initial bid creates a new trade"() {
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
                        .withName("item a")
                        .withOwner(agathaUser)
                        .build()
        )

        storageServiceStubber.stubItems(
                buildItem()
                        .withId("b")
                        .withName("item b")
                        .withOwner(gregUser)
                        .build()
        )



        def token = tokenProvider.createToken(new TestingAuthenticationToken("greg", "123", newArrayList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))), false)
        def request = given()
                .contentType(ContentType.JSON)
                .body('{ "requestedItems" : [{ "itemId" : "a" }], "offeredItems": [{ "itemId" : "b" }] }')
                .header("Authorization", "Bearer " + token)

        when:
        def response = request.when().post("/bids")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("latestBid.json"))
                .body("placingBidder.login", equalTo("greg"))
                .body("requestedItems", hasSize(1))
                .body("requestedItems[0].itemId", equalTo("a"))
                .body("requestedItems[0].name", equalTo("item a"))
                .body("offeredItems", hasSize(1))
                .body("offeredItems[0].itemId", equalTo("b"))
                .body("offeredItems[0].name", equalTo("item b"));
    }

}
