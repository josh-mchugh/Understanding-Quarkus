package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.Map;

@QuarkusTest
public class MyLivenessCheckTest {
    
    @Test
    public void whenAliveCheckExpectUp() {

        Map<String, String> expected = Map.of(
            "name", "alive",
            "status", "UP"
        );

        given()
            .when()
                .get("/q/health/live")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("checks", hasItem(expected));
    }
}
