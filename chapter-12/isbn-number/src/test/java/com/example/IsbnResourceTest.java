package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(IsbnResource.class)
public class IsbnResourceTest {
    
    @Test
    public void whenRequestIsValidThenExpectOk() {

        given()
            .when()
                .get()
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenRequestIsValidThenExpectBody() {

        given()
            .when().get()
            .then()
                .body("isbn10", is(notNullValue()))
                .body("isbn13", is(notNullValue()));
    }
}
