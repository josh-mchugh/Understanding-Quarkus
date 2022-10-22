package com.example;

import io.quarkus.test.junit.QuarkusTest;
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
                .get("/q/metrics/application")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(CoreMatchers.containsString("application_com_example_GreetingResource_hello_total 1.0"));
    }

}