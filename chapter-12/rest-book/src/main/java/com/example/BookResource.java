package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.example.numbers.IsbnNumbers;
import com.example.numbers.NumberResourceProxy;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    
    private final NumberResourceProxy numberResourceProxy;

    public BookResource(@RestClient NumberResourceProxy numberResourceProxy) {
        this.numberResourceProxy = numberResourceProxy;
    }

    @GET
    public Response getRandomBook() {

        IsbnNumbers numbers = numberResourceProxy.generateIsbnNumbers();

        return Response.ok().build();
    }
}
