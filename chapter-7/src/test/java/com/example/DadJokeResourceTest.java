package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class DadJokeResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when()
            .get("/dad/joke")
          .then()
             .statusCode(Response.Status.OK.getStatusCode())
             .body(is(" My dog used to chase people on a bike a lot. It got so bad I had to take his bike away."));
    }

}