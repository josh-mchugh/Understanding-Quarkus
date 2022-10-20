package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/hello")
public class GreetingResource {

    private final List<String> GREETINGS = new ArrayList<>(List.of(
        "Hello",
        "hola",
        "bonjour",
        "guten tag",
        "salve",
        "nǐn hǎo",
        "olá",
        "asalaam alaikum",
        "konnichiwa",
        "anyoung haseyo",
        "Zdravstvuyte"
    ));

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> getDefaultGreeting(@QueryParam("name") String name) {

        return Uni.createFrom()
            .item(formatGreeting("Hello", name));
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<String> getDefaultGreetings(@QueryParam("name") String name) {

        return Multi.createFrom().items(GREETINGS.stream())
            .map(greeting -> formatGreeting(greeting, name));
    }

    private String formatGreeting(String greeting, String name) {

        return String.format("%s %s", greeting, name);
    }
}