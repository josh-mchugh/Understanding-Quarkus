package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.github.javafaker.Faker;

@Path("/api/numbers")
public class IsbnResource {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Generates ISBN numbers",
        description = "These ISBN numbers have several formats: ISBN 13 and ISBN 10"
    )
    @APIResponse(
        responseCode = "200",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON, 
            schema = @Schema(implementation = IsbnNumbers.class)
        )
    )
    public Response getIsbnNumbers() {

        Faker faker = new Faker();

        IsbnNumbers numbers = new IsbnNumbers()
            .setIsbn10(faker.code().isbn10(true))
            .setIsbn13(faker.code().isbn13(true));

        return Response.ok(numbers).build();
    }
}
