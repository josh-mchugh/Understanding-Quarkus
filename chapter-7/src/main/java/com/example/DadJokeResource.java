package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dad")
public class DadJokeResource {

    @GET
    @Path("/joke")
    @Produces(MediaType.TEXT_PLAIN)
    public String getDadJoke() {

        return " My dog used to chase people on a bike a lot. It got so bad I had to take his bike away.";
    }
}