package com.exmaple;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

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
    @Disabled(value = "Example of disabled test")
    public void exampleOfDisabledTest() {

    }

}