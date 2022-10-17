package com.example;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

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
    @Operation(summary = "Returns a static plain text response")
    @APIResponse(
        responseCode = "200",
        description = "Plain text message"
    )
    public String hello() {

        return "Hello this is an exmple of plain text";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns a list of Authors")
    @APIResponse(
        responseCode = "200",
        description = "The whole list of Authors"
    )
    public Response getAuthors() {

        return Response.ok(authors).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Returns an Author by a given id")
    @APIResponse(
        responseCode = "200",
        description = "Author with the requested id"
    )
    @APIResponse(
        responseCode = "204",
        description = "No Author found with the requested id"
    )
    public Response getAuthor(@PathParam("id") Integer id) {

        Optional<Author> authorOptional = findAuthorById(id);

        return authorOptional.isPresent() 
            ? Response.ok(authorOptional.get()).build()
            : Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Adds an Author")
    @APIResponse(
        responseCode = "200",
        description = "Author added to the system"
    )
    @APIResponse(
        responseCode = "400",
        description = "Request parameters are invalid"
    )
    public Response postAuthor(Author author) {

        if(author == null) {

            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        author.id(authors.size() + 1);

        authors.add(author);

        URI uri = UriBuilder.fromPath(String.format("/author/%s", author.getId())).build();

        return Response.created(uri).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Updates an Author by id")
    @APIResponse(
        responseCode = "200",
        description = "Successfully updated the Author by requested id"
    )
    @APIResponse(
        responseCode = "204",
        description = "Author not found by requested id"
    )
    public Response putAuthor(@PathParam("id") Integer id, Author updatedAuthor) {

        Optional<Author> authorOptional = findAuthorById(id);

        if(authorOptional.isPresent()) {
            
            Author author = authorOptional.get();
            author.setFirstName(updatedAuthor.getFirstName());
            author.setLastName(updatedAuthor.getLastName());

            return Response.ok(author).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Remove an Author by id")
    @APIResponse(
        responseCode = "200",
        description = "Author successfully removed by requested id"
    )
    @APIResponse(
        responseCode = "204",
        description = "Author not found by requested id"
    )
    public Response deleteAuthor(@PathParam("id") Integer id) {

        Optional<Author> authorOptional = findAuthorById(id);

        if(authorOptional.isPresent()) {

            authors.removeIf(author -> author.getId().equals(id));

            return Response.ok().build();
        }

        return Response.noContent().build();
    }

    private Optional<Author> findAuthorById(Integer id) {

        return authors.stream()
            .filter(author -> author.getId().equals(id))
            .findFirst();
    }
}