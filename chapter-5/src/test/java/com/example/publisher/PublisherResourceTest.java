package com.example.publisher;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.common.mapper.TypeRef;

import static io.restassured.RestAssured.get;
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
    public void whenGetPublisherHasResultThenExpectResult() {

        Publisher publisher = new Publisher();
        publisher.name = "test";

        List<Publisher> expected = Arrays.asList(publisher);
        Mockito.when(publisherService.findAll()).thenReturn(expected);

        get().then().body("size()", is(1));
    }
}
