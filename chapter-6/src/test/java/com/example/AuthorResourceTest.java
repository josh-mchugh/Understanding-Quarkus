package com.example;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;

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

    @Test
    public void whenGetAuthorByIdHasValidIdThenExpectOk() {
      given()
        .when()
          .get("/{id}", 1)
        .then()
          .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenGetAuthorByIdHasInvalidIdThenExpectNoContent() {
      given()
        .when()
          .get("/{id}", Integer.MIN_VALUE)
        .then()
          .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void whenGetAuthorByIdIsValidExpectJsonKeys() {
      given()
        .when()
          .get("/{id}", 1)
        .then()
          .body("$", hasKey("first_name"))
          .body("$", hasKey("last_name"));
    }

    @Test
    public void whenGetAuthorByIdIsValidExpectValues() {
      given()
        .when()
          .get("/{id}", 1)
        .then()
          .body("first_name", is("H.P."))
          .body("last_name", is("Lovecraft"));
    }
}