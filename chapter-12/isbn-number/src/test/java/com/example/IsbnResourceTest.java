package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasKey;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(IsbnResource.class)
public class IsbnResourceTest {
    
    @Test
    public void whenRequestIsValidThenExpectOk() {

        given()
            .when().get()
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenRequestIsValidThenExpectBody() {

        given()
            .when().get()
            .then()
                .body("$", hasKey("isbn_10"))
                .body("$", hasKey("isbn_13"))
                .body("isbn_10", is(notNullValue()))
                .body("isbn_13", is(notNullValue()));
    }
}
