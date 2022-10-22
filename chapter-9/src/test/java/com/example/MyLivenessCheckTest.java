package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class MyLivenessCheckTest {
    
    @Test
    public void whenCanIHazDadJakesCheckExpectUp() {

        given()
            .when()
                .get("/q/health/live")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("checks[0].name", is("Mic Check.. 1.. 2.."))
                .body("checks[0].status", is("UP"));
    }
}
