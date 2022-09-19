package com.example;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@Path("/artists")
public class ArtistResource {

    private static final List<Artist> artists = List.of(
      new Artist().id(UUID.randomUUID()).firstName("John").lastName("Lennon"),
      new Artist().id(UUID.randomUUID()).firstName("Paul").lastName("McCartney"),
      new Artist().id(UUID.randomUUID()).firstName("George").lastName("Harrison"),
      new Artist().id(UUID.randomUUID()).firstName("Ringo").lastName("Starr")
    );

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllArtists() {
        return Response.ok(artists).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Integer getArtistsCount() {
        return artists.size();
    }
}