package com.example;

import io.quarkus.test.Mock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import javax.ws.rs.core.Response;

@QuarkusTest
public class DadJokeResourceTest {

    @InjectMock
    @RestClient
    JokeClient jokeClient;

    @Test
    public void testDadJoke() {

        Joke joke = new Joke();
        joke.setJoke("My dog used to chase people on a bike a lot. It got so bad I had to take his bike away.");
        
        Mockito.when(jokeClient.getDadJoke()).thenReturn(joke);

        given()
          .when()
            .get("/dad/joke")
          .then()
             .statusCode(Response.Status.OK.getStatusCode())
             .body(is("My dog used to chase people on a bike a lot. It got so bad I had to take his bike away."));
    }

}