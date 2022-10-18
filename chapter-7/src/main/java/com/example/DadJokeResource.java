package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/dad")
public class DadJokeResource {

    private final JokeClient jokeClient;

    public DadJokeResource(@RestClient JokeClient jokeClient) {
        this.jokeClient = jokeClient;
    }

    @GET
    @Path("/joke")
    @Produces(MediaType.TEXT_HTML)
    public String getDadJoke() {

        return jokeClient.getDadJoke().getJoke();
    }
}