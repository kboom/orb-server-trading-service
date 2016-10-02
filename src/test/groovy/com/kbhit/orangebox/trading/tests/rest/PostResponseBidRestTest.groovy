package com.kbhit.orangebox.trading.tests.rest

import com.jayway.restassured.http.ContentType
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.stubs.feign.StorageServiceStubber
import com.kbhit.orangebox.trading.stubs.feign.UserServiceStubber
import org.springframework.beans.factory.annotation.Autowired

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static com.kbhit.orangebox.trading.dbsetup.data.InsertOngoingTrade.ONGOING_TRADE_ID
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyItems.dummyItem
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.agathaUser
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.gregUser
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.core.IsEqual.equalTo

class PostResponseBidRestTest extends RestTest {

    private static final User agatha = agathaUser().build()
    private static final User greg = gregUser().build()

    @Autowired
    DbSetupTestDataLoader testDataLoader

    @Autowired
    UserServiceStubber userServiceStubber;

    @Autowired
    StorageServiceStubber storageServiceStubber;

    @Autowired
    RestTestDriver restTestDriver

    @Autowired
    SecurityFilter securityFilter

    def "Posting response bid changes existing trade"() {
        given:
        testDataLoader.createDummyBidders()
        testDataLoader.createDummyTrade()
        restTestDriver.thereAreUsers(agatha, greg)
        restTestDriver.thereAreItems(dummyItem(agatha, "a-1"), dummyItem(agatha, "a-2"))
        restTestDriver.thereAreItems(dummyItem(greg, "g-1"), dummyItem(greg, "g-2"))

        def request = given()
                .pathParam("tradeId", ONGOING_TRADE_ID)
                .filter(securityFilter.setLogin("agatha"))
                .contentType(ContentType.JSON)
                .body('{ "placingBidder": { "login" : "agatha" }, "respondingBidder" : { "login" : "greg" }, "requestedItems" : [{ "itemId" : "g-1" },{ "itemId" : "g-2" }], "offeredItems": [{ "itemId" : "a-1" },{ "itemId" : "a-2" }] }')

        when:
        def response = request.when().post("/trades/{tradeId}/bids")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("bid.json"))
                .body("placingBidder.login", equalTo("agatha"))
                .body("requestedItems", hasSize(2))
                .body("requestedItems[0].itemId", equalTo("g-1"))
                .body("requestedItems[1].itemId", equalTo("g-2"))
                .body("requestedItems[0].name", equalTo("item g-1"))
                .body("requestedItems[1].name", equalTo("item g-2"))
                .body("offeredItems", hasSize(2))
                .body("offeredItems[0].itemId", equalTo("a-1"))
                .body("offeredItems[1].itemId", equalTo("a-2"))
                .body("offeredItems[0].name", equalTo("item a-1"))
                .body("offeredItems[1].name", equalTo("item a-2"));
    }

}
