package org.yugo.services.restaurant;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.util.Map;

import org.hamcrest.Matchers;

@QuarkusTest
@TestHTTPEndpoint(RestaurantController.class)
public class RestaurantControllerTest {

    @Test
    void When_GetAll_Should_ReturnAll() {
        when().get()    
        .then()
           .statusCode(200)
           .body("size()", Matchers.equalTo(200));
    }

    @Test
    void When_MatchParam_Should_ReturnMatches() {
        var searchParameter = Map.of(
            "name", "Tasty",
            "price", 40,
            "distance", 10,
            "customerRating", 1,
            "cuisine", "Chin");

        given()
            .contentType(ContentType.JSON)
            .body(searchParameter)
        .when()
            .post()
        .then()
           .statusCode(200)
           .body("size()", Matchers.greaterThan(0));
    }

    @Test
    void When_PriceUnmatch_Should_ReturnEmpty() {
        var searchParameter = Map.of(
            "name", "Tasty",
            "price", 10,
            "distance", 10,
            "customerRating", 1,
            "cuisine", "Chin");

        given()
            .contentType(ContentType.JSON)
            .body(searchParameter)
        .when()
            .post()
        .then()
           .statusCode(200)
           .body("size()", Matchers.equalTo(0));
    }


    @Test
    void When_CuisineUnmatch_Should_ReturnEmpty() {
        var searchParameter = Map.of(
            "name", "Tasty",
            "price", 50,
            "distance", 10,
            "customerRating", 1,
            "cuisine", "FakeCuisine");

        given()
            .contentType(ContentType.JSON)
            .body(searchParameter)
        .when()
            .post()
        .then()
           .statusCode(200)
           .body("size()", Matchers.equalTo(0));
    }
}
