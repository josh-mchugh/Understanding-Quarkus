package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
public class BookResource {
    
    @GET
    public Response getRandomBook() {

        return Response.ok().build();
    }
}
