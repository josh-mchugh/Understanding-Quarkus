package com.example.publisher;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.endsWith;

@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {

    @InjectMock
    private PublisherService publisherService;

    @Test
    public void whenGetPublishersHasNoResultsExpectOk() {

        Mockito.when(publisherService.findAll()).thenReturn(List.of());

        get().then().statusCode(Response.Status.OK.getStatusCode());
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

        get().then().statusCode(Response.Status.OK.getStatusCode());
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

        given()
            .param("name", "test")
            .get()
        .then()
            .statusCode(Response.Status.OK.getStatusCode());
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

        given()
            .param("name", "test")
            .get()
        .then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void whenGetPublisherByIdHasResultThenExpectOk() {
        
        Mockito.when(publisherService.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(new Publisher()));

        get("/{id}", 1L)
        .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenGetPublisherByDoesNotHaveResultThenExpectNoContent() {

        get("/{id}", 1L)
        .then()
            .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

    @Test
    public void whenGetPublisherByIdHasResultThenExpectResult() {

        Publisher publisher = new Publisher();
        publisher.id = 1L;
        publisher.name = "test";

        Mockito.when(publisherService.findById(Mockito.anyLong()))
            .thenReturn(Optional.of(publisher));

        get("/{id}", 1L)
        .then()
            .body("id", is(1))
            .body("name", is("test"));
    }

    @Test
    public void whenPostPublisherThenExpectCreated() {

        Publisher publisher = new Publisher();
        publisher.id = 1L;

        Mockito.doNothing().when(publisherService).persist(Mockito.any(Publisher.class));

        given()
            .contentType(ContentType.JSON)
            .body(publisher, ObjectMapperType.JSONB)
            .post()
        .then()
            .statusCode(Response.Status.CREATED.getStatusCode());
    }

    @Test
    public void whenPostPublisherThenExpectLocation() {

        Publisher publisher = new Publisher();
        publisher.id = 1L;

        Mockito.doNothing().when(publisherService).persist(Mockito.any(Publisher.class));

        given()
            .contentType(ContentType.JSON)
            .body(publisher, ObjectMapperType.JSONB)
            .post()
        .then()
            .header("location", endsWith("/publishers/1"));
    }

    @Test
    public void whenDeletePublisherByNameThenExpectOk() {

        Mockito.doNothing().when(publisherService).deleteByName(Mockito.anyString());

        given()
            .param("name", "test")
            .delete()
        .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    public void whenDeletePublisherByIdThenExpectOk() {

        Mockito.doNothing().when(publisherService).deleteByName(Mockito.anyString());

        given()
            .param("id", "1")
            .delete()
        .then()
            .statusCode(Response.Status.OK.getStatusCode());
    }
}
