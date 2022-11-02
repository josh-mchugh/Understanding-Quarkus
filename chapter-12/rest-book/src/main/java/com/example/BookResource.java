package com.example;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.example.numbers.IsbnNumbers;
import com.example.numbers.NumberResourceProxy;
import com.github.javafaker.Faker;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    
    private final NumberResourceProxy numberResourceProxy;

    public BookResource(@RestClient NumberResourceProxy numberResourceProxy) {
        this.numberResourceProxy = numberResourceProxy;
    }

    @GET
    @Fallback(fallbackMethod = "getRandomBookFallback")
    @Counted(
        name = "getRandomBook",
        description = "Counts how many times the getRandomBook method has been invoked"
    )
    public Response getRandomBook() {

        IsbnNumbers numbers = numberResourceProxy.generateIsbnNumbers();

        Faker faker = new Faker();

        JsonObject book = Json.createObjectBuilder()
            .add("isbn_13", numbers.getIsbn13())
            .add("isbn_10", numbers.getIsbn10())
            .add("title", faker.book().title())
            .add("author", faker.book().author())
            .add("genre", faker.book().genre())
            .add("publisher", faker.book().publisher())
            .add("timestamp", faker.date().past(365 * 100, TimeUnit.DAYS).toString())
            .build();

        return Response.ok(book).build();
    }

    private Response getRandomBookFallback() {

        JsonObject book = Json.createObjectBuilder()
            .add("title", "Fallback Book")
            .add("timestamp", LocalDateTime.now().toString())
            .build();

            return Response.ok(book).build();
    }
}
