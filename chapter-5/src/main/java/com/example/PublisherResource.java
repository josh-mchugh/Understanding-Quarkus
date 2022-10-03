package com.example;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;

@Path("/publishers")
@Transactional
public class PublisherResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublishers(@QueryParam("name") String name) {

        if(name != null) {

            Optional<Publisher> publisher = Publisher.findByName(name);

            return publisher.isPresent()
                    ? Response.ok(publisher.get()).build()
                    : Response.noContent().build();
        }

        return Response.ok(Publisher.findAll().list()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPublisherByName(@PathParam("id") Long id) {

        Publisher publisher = Publisher.findById(id);

        return publisher != null
                ? Response.ok(publisher).build()
                : Response.noContent().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putPublisher(Publisher publisher) {

        publisher.persist();

        return Response.created(URI.create(String.format("/publishers/%s", publisher.id))).build();
    }

    @DELETE
    public Response deletePublisherByName(@QueryParam("name") String name) {

        Publisher.deleteByName(name);

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePublisher(@PathParam("id") Long id) {

        Publisher.deleteById(id);

        return Response.ok().build();
    }
}
