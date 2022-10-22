package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Timed;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Timed(
        name = "timeHello",
        description = "Times how long it tests to invloke the hello method",
        unit = MetricUnits.MILLISECONDS
    )
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }
}