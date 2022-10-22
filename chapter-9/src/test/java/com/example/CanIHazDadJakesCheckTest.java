package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.Map;

@QuarkusTest
public class CanIHazDadJakesCheckTest {
    
    @Test
    public void whenCanIHazDadJakesCheckExpectUp() {

        Map<String, Object> expected = Map.of(
            "name", "Can I Haz Dad Joke",
            "status", "UP",
            "data", Map.of("host", "GET https://icanhazdadjoke.com/")
        );

        given()
            .when()
                .get("/q/health/live")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("checks", hasItem(expected));;
    }
}
