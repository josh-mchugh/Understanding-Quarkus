package com.example.publisher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {

    @InjectMock
    private PublisherService publisherService;

    @Test
    public void whenGetPublishersHasNoResultsExpectOk() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        get().then().statusCode(200);
    }

    @Test
    public void whenGetPublishersHasNoResultsExpectNoResults() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        List<Publisher> result = get().as(new TypeRef<List<Publisher>>() {});

        Assertions.assertEquals(List.of(), result);
    }

    @Test
    public void whenGetPublishersHasResultExpectOk() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        Mockito.when(publisherService.findAll()).thenReturn(List.of(publisher));

        get().then().statusCode(200);
    }

    @Test
    public void whenGetPublisherHasResultThenExpectResults() {

        Mockito.when(publisherService.findAll())
            .thenReturn(Arrays.asList(new Publisher()));

        get().then().body("size()", is(1));
    }

    @Test
    public void whenGetPublishersWithNameHasResultExpectOk() {

        Optional<Publisher> expected = Optional.of(new Publisher());

        Mockito.when(publisherService.findByName(Mockito.anyString()))
            .thenReturn(expected);

        given().param("name", "test").get()
            .then().statusCode(200);
    }

    @Test
    public void whenGetPublishersWithNameHasResultExpectResult() {

        Publisher publisher = new Publisher();
        publisher.id = 1L;

        Mockito.when(publisherService.findByName(Mockito.anyString()))
            .thenReturn(Optional.of(publisher));

        Optional<Publisher> result = given().param("name", "test")
            .get().as(new TypeRef<Optional<Publisher>>() {});

        Assertions.assertEquals(publisher.id, result.get().id);
    }

    @Test
    public void whenGetPublishersWithNameHasNoResultExpectNoContent() {
        
        Mockito.when(publisherService.findByName(Mockito.anyString()))
            .thenReturn(Optional.empty());

        given().param("name", "test")
            .get().then().statusCode(204);
    }
}
