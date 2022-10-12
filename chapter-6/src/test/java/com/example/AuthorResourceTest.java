package com.example;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
@TestHTTPEndpoint(AuthorResource.class)
public class AuthorResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when()
            .get("/hello")
          .then()
             .statusCode(Response.Status.OK.getStatusCode())
             .body(is("Hello this is an exmple of plain text"));
    }

    @Test
    public void whenGetAuthorThenExpectStatusOk() {
      given()
        .when()
          .get()
        .then()
          .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenGetAuthorThenExpectResponseSize() {
      given()
        .when()
          .get()
        .then()
          .body("$.size()", is(5));
    }
}