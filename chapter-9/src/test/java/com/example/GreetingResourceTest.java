package com.example;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasKey;

import javax.ws.rs.core.Response;

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
    public void testHelloEndpointMetrics() {

        given().when().get("/hello");

        given()
            .when()
                .accept(ContentType.JSON)
                .get("/q/metrics/application")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("'com.example.GreetingResource.timeHello'.count", is(1))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p99"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("min"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("max"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("mean"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p50"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p999"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("stddev"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p95"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p98"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("p75"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("fiveMinRate"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("fifteenMinRate"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("meanRate"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("oneMinRate"))
                .body("'com.example.GreetingResource.timeHello'", hasKey("elapsedTime"));
    }

}