package com.example;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/author")
public class AuthorResource {

    private final List<Author> authors = List.of(
        new Author().id(1).firstName("H.P.").lastName("Lovecraft"),
        new Author().id(2).firstName("August").lastName("Derleth"),
        new Author().id(3).firstName("Robert").lastName("Bloch"),
        new Author().id(4).firstName("Clark").lastName("Smith"),
        new Author().id(5).firstName("Robert").lastName("Howard")
    );

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {

        return "Hello this is an exmple of plain text";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthors() {

        return Response.ok(authors).build();
    }
}