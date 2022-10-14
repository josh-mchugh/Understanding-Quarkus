package com.example;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/author")
public class AuthorResource {

    private List<Author> authors = new ArrayList<>(List.of(
        new Author().id(1).setFirstName("H.P.").setLastName("Lovecraft"),
        new Author().id(2).setFirstName("August").setLastName("Derleth"),
        new Author().id(3).setFirstName("Robert").setLastName("Bloch"),
        new Author().id(4).setFirstName("Clark").setLastName("Smith"),
        new Author().id(5).setFirstName("Robert").setLastName("Howard")
    ));

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postAuthor(Author author) {

        author.id(authors.size() + 1);

        authors.add(author);

        URI uri = UriBuilder.fromPath(String.format("/author/%s", author.getId())).build();

        return Response.created(uri).build();
    }
}