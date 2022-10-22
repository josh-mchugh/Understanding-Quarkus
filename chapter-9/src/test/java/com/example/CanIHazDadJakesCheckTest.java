package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CanIHazDadJakesCheckTest {
    
    @Test
    public void whenCanIHazDadJakesCheckExpectUp() {

        given()
            .when()
                .get("/q/health/live")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("checks[2].name", is("Can I Haz Dad Joke"))
                .body("checks[2].status", is("UP"))
                .body("checks[2].data.host", is("GET https://icanhazdadjoke.com/"));
    }
}
