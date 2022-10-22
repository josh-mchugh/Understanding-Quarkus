package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
            .statusCode(Response.Status.OK.getStatusCode())
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testHelloEndpointCounted() {

        given().when().get("/hello");

        given()
            .when()
                .accept(ContentType.JSON)
                .get("/q/metrics/application")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("'com.example.GreetingResource.hello'", is(1));
    }

}