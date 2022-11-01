package com.example;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class BookResourceTest {

    @Test
    public void whenGetRandomBookIsValidThenExpectOk() {

        given()
            .when().get()
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
