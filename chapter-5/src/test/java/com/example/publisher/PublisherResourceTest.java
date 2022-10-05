package com.example.publisher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;

@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {
    
    @InjectMock
    private PublisherService publisherService;

    @Test
    public void whenGetPublishersHasNoResultsExpectOk() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        RestAssured.given()
            .when().get()
            .then().statusCode(200);
    }

    @Test
    public void whenGetPublishersHasNoResultsExpectNoResults() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        List<Publisher> result = RestAssured.given()
            .when().get()
            .as(new TypeRef<List<Publisher>>() {});

        Assertions.assertEquals(List.of(), result);
    }

    @Test
    public void whenGetPublishersHasResultExpectOk() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        Mockito.when(publisherService.findAll()).thenReturn(List.of(publisher));

        RestAssured.given()
            .when().get()
            .then().statusCode(200);
    }

    @Test
    public void whenGetPublisherHasResultThenExpectResult() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        List<Publisher> expected = Arrays.asList(publisher);
        Mockito.when(publisherService.findAll()).thenReturn(expected);

        List<Publisher> result = (List<Publisher>) RestAssured.given()
            .when().get()
            .as(new TypeRef<List<Publisher>>() {});

        Assertions.assertEquals(expected, result);
    }
}
