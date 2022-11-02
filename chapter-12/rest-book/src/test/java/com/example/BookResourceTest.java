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

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
public class BookResourceTest {

    @InjectMock
    @RestClient
    NumberResourceProxy numberResourceProxy;

    @Test
    public void whenGetRandomBookIsValidThenExpectOk() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(getIsbnNumbers());

        given()
            .when().get()
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenGetRandomBookIsValidThenExpectBody() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(getIsbnNumbers());

        given()
            .when().get()
            .then()
                .body("isbn_10", is("isbn10"))
                .body("isbn_13", is("isbn_13"))
                .body("title", notNullValue())
                .body("author", notNullValue())
                .body("genre", notNullValue())
                .body("publisher", notNullValue())
                .body("timestamp", notNullValue());
    }

    private IsbnNumbers getIsbnNumbers() {

        IsbnNumbers numbers = new IsbnNumbers();
        numbers.setIsbn10("isbn10");
        numbers.setIsbn13("isbn13");

        return numbers;
    }
}
