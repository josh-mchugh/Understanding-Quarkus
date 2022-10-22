package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Counted(
        name = "hello",
        description = "Counts how many times hello resource endpoint has been invoked"
    )
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}