package com.kbhit.orangebox.trading.tests.rest

import com.jayway.restassured.http.ContentType
import com.kbhit.orangebox.trading.dbsetup.DbSetupTestDataLoader
import com.kbhit.orangebox.trading.domain.User
import com.kbhit.orangebox.trading.stubs.feign.StorageServiceStubber
import com.kbhit.orangebox.trading.stubs.feign.UserServiceStubber
import org.springframework.beans.factory.annotation.Autowired

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyItems.dummyItem
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.agathaUser
import static com.kbhit.orangebox.trading.stubs.domain.dummies.DummyUsers.gregUser
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.hamcrest.core.IsEqual.equalTo

class PostInitialBidRestTest extends RestTest {

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

    def "Posting initial bid creates a new trade"() {
        given:
        restTestDriver.thereAreUsers(agatha, greg)
        restTestDriver.thereAreItems(dummyItem(agatha, "a-1"))
        restTestDriver.thereAreItems(dummyItem(greg, "g-1"))

        def request = given().
                filter(securityFilter.setLogin("greg"))
                .contentType(ContentType.JSON)
                .body('{ "placingBidder": { "login" : "greg" }, "respondingBidder" : { "login" : "agatha" }, "requestedItems" : [{ "itemId" : "a-1" }], "offeredItems": [{ "itemId" : "g-1" }] }');

        when:
        def response = request.when().post("/bids")

        then:
        response.then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("bid.json"))
                .body("placingBidder.login", equalTo("greg"))
                .body("requestedItems", hasSize(1))
                .body("requestedItems[0].itemId", equalTo("a-1"))
                .body("requestedItems[0].name", equalTo("item a-1"))
                .body("offeredItems", hasSize(1))
                .body("offeredItems[0].itemId", equalTo("g-1"))
                .body("offeredItems[0].name", equalTo("item g-1"));
    }


}
