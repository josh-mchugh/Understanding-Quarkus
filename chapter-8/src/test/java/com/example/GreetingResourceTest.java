package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void whenDefaultGreetingExpectMessage() {
        given()
          .when()
            .get("/hello?name=test")
          .then()
             .statusCode(Response.Status.OK.getStatusCode())
             .body(is("Hello test"));
    }
    
    @Test
    public void whenGreetingAllExpectSize() {

        given()
            .when()
                .get("/hello/all?name=test")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(10));
    }

}