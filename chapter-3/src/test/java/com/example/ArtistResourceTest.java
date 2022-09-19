package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ArtistResourceTest {

    @Test
    public void shouldGetAllArtists() {
        given()
          .when().get("/artists")
          .then()
             .statusCode(200)
             .body("size()", equalTo(4));
    }

    @Test
    public void shouldGetArtistsCount() {
        given()
            .when().get("artists/count")
            .then()
                .assertThat().statusCode(is(200))
            .and()
                .body(is("4"));
    }

}