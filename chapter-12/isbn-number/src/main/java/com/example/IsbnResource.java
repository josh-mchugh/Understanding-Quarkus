package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.javafaker.Faker;

@Path("/api/numbers")
public class IsbnResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIsbnNumbers() {

        Faker faker = new Faker();

        IsbnNumbers numbers = new IsbnNumbers()
            .setIsbn10(faker.code().isbn10(true))
            .setIsbn13(faker.code().isbn13(true));

        return Response.ok(numbers).build();
    }
}
