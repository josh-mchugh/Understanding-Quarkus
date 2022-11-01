package com.example.numbers;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api/numbers")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient
@Singleton
public interface NumberResourceProxy {
    
    @GET
    IsbnNumbers generateIsbnNumbers();

}
