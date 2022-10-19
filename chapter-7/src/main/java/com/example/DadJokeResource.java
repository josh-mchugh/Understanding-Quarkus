package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
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
    @Timeout(250)
    @Fallback(fallbackMethod = "jokeFallback")
    @CircuitBreaker(
        requestVolumeThreshold = 4,
        failureRatio = 0.5,
        successThreshold = 2,
        delay = 2000
    )
    public String getDadJoke() {        

        return jokeClient.getDadJoke().getJoke();
    }

    public String jokeFallback() {

        return "A horse walks into a bar. The bar tender says\"Hey.\" The horse says \"Sure.\"";
    }
}