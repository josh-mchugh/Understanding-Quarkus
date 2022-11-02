package com.example;

import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import com.example.numbers.IsbnNumbers;
import com.example.numbers.NumberResourceProxy;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.when;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;


@QuarkusTest
public class BookResourceTest {

    @InjectMock
    @RestClient
    NumberResourceProxy numberResourceProxy;

    @Test
    public void whenGetRandomBookIsValidThenExpectOk() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(getIsbnNumbers());

        given()
            .when()
                .get("/api/books")
            .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenGetRandomBookIsValidThenExpectBody() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(getIsbnNumbers());

        given()
            .when()
                .get("/api/books")
            .then()
                .body("isbn_10", is("isbn10"))
                .body("isbn_13", is("isbn13"))
                .body("title", notNullValue())
                .body("author", notNullValue())
                .body("genre", notNullValue())
                .body("publisher", notNullValue())
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenGetRandomBookFallsBackExpectBody() {

        when(numberResourceProxy.generateIsbnNumbers()).thenThrow(RuntimeException.class);

        given()
            .when()
                .get("/api/books")
            .then()
                .body("title", is("Fallback Book"))
                .body("timestamp", notNullValue());
    }

    @Test
    public void whenGetRandomBookIsValidThenExpectCountedMetrics() {

        when(numberResourceProxy.generateIsbnNumbers()).thenReturn(getIsbnNumbers());

        given().when().get("/api/books");

        given()
            .when()
                .accept(ContentType.JSON)
                .get("/q/metrics/application")
            .then() 
                .statusCode(Response.Status.OK.getStatusCode())
                .body("'com.example.BookResource.getRandomBook'", greaterThanOrEqualTo(1));
    }

    private IsbnNumbers getIsbnNumbers() {

        IsbnNumbers numbers = new IsbnNumbers();
        numbers.setIsbn10("isbn10");
        numbers.setIsbn13("isbn13");

        return numbers;
    }
}
