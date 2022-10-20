package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void whenDefaultGreetingExpectMessage() {
        given()
          .when()
            .get("/hello?name=test")
          .then()
             .statusCode(200)
             .body(is("Hello test"));
    }

}