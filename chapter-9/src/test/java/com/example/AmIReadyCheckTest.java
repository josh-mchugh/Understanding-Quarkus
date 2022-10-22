package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AmIReadyCheckTest {
    
    @Test
    public void whenAmIReadyCheckExpectUp() {

        given()
            .when()
                .get("/q/health/ready")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("checks[0].name", is("Ready or not... here we go"))
                .body("checks[0].status", is("UP"));
    }
}
