package com.example.book.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void whenAddBookIsSuccessExpect200() {
        RestAssured.given()
                .when().get("/book/add")
                .then().statusCode(RestResponse.StatusCode.OK);
    }

    @Test
    public void whenRemoveBookIsSuccessExpect200() {
        RestAssured.given()
                .when().get("/book/remove?isbn=13-12345-12345678")
                .then().statusCode(RestResponse.StatusCode.OK);
    }
}
