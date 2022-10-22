package com.example;

import io.quarkus.test.junit.QuarkusTest;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.Map;

@QuarkusTest
public class MicCheckTest {
    
    @Test
    public void whenMicCheckExpectUp() {

        Map<String, String> expected = Map.of(
            "name", "Mic Check.. 1.. 2..",
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
