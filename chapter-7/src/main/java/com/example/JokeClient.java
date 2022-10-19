package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "https://icanhazdadjoke24.com/")
public interface JokeClient {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Joke getDadJoke();
}
