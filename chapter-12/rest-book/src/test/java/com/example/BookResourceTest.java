package com.example;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.example.numbers.IsbnNumbers;
import com.example.numbers.NumberResourceProxy;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class BookResourceTest {

    @InjectMock
    @RestClient
    NumberResourceProxy numberResourceProxy;

    @Test
    public void whenGetRandomBookIsValidThenExpectOk() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(new IsbnNumbers());

        given()
            .when().get()
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }
}
