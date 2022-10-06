package com.example.publisher;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.json.bind.Jsonb;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;


@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {
    
    @Inject
    Jsonb jsonb;

    @InjectMock
    private PublisherService publisherService;

    @Test
    public void whenGetPublishersHasNoResultsExpectOk() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        RestAssured.get().then().statusCode(200);
    }

    @Test
    public void whenGetPublishersHasNoResultsExpectNoResults() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        List<Publisher> result = RestAssured.given().get()
            .as(new TypeRef<List<Publisher>>() {});

        Assertions.assertEquals(List.of(), result);
    }

    @Test
    public void whenGetPublishersHasResultExpectOk() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        Mockito.when(publisherService.findAll()).thenReturn(List.of(publisher));

        RestAssured.get().then().statusCode(200);
    }

    @Test
    public void whenGetPublisherHasResultThenExpectResult() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        List<Publisher> expected = Arrays.asList(publisher);
        Mockito.when(publisherService.findAll()).thenReturn(expected);

        RestAssured.get()
            .then().body("[0].name", CoreMatchers.is("test"));
    }
}
