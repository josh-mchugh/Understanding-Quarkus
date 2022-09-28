package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class ArtistResourceTest {

    @Test
    public void shouldGetAllArtists() {
        given()
          .when().get("/artists")
          .then()
             .statusCode(Response.Status.OK.getStatusCode())
             .body("size()", equalTo(4));
    }

    @Test
    public void shouldGetArtistsCount() {
        given()
            .when().get("/artists/count")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is("4"));
    }

    @Test
    public void shouldGetArtistExpectFirstJohnLennon() {
        given()
            .when().get("/artists")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("first_name[0]", is("John"))
                .body("last_name[0]", is("Lennon"));
    }

}