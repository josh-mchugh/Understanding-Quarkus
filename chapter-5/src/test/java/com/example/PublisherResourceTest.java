package com.example;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;

@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {
    
    @Test
    public void whenGetFindAllHasNoResultsExpectOk() {

        RestAssured.given()
            .when().get()
            .then()
                .statusCode(200)
                .extract().body().asString().equals("[]");
    }
}
