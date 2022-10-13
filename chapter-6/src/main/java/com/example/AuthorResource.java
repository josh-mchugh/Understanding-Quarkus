package com.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/author")
public class AuthorResource {

    private List<Author> authors = Arrays.asList(
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

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthor(@PathParam("id") Integer id) {

        Optional<Author> authorOptional = authors.stream()
            .filter(author -> author.getId().equals(id))
            .findFirst();

        return authorOptional.isPresent() 
            ? Response.ok(authorOptional.get()).build()
            : Response.noContent().build();
    }
}