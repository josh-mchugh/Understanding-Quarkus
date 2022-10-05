package com.example;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.vertx.core.json.JsonArray;

@QuarkusTest
@TestHTTPEndpoint(PublisherResource.class)
public class PublisherResourceTest {
    
    @Test
    public void whenGetPublishersHasNoResultsExpectOk() {

        RestAssured.given()
            .when().get()
            .then()
                .statusCode(200)
                .extract().toString().equals("[]");
    }

    @Test
    public void whenGetPublishersHasResultExpectOk() {

        PanacheMock.mock(Publisher.class);

        Publisher publisher = new Publisher();
        publisher.name = "test";

        PanacheQuery query = Mockito.mock(PanacheQuery.class);
        Mockito.when(Publisher.findAll()).thenReturn(query);
        Mockito.when(query.list()).thenReturn(List.of(publisher));

        RestAssured.given()
            .when().get()
            .then()
                .statusCode(200)
                .extract().as(List.class).equals(List.of(publisher));
    }
}
