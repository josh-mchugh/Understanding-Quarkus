package com.example.book.resource;

import com.example.book.service.BookService;
import com.example.book.model.Book;
import com.example.book.service.annotation.Legacy;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/book")
public class BookResource {

    private final BookService bookService;
    private final BookService legacyBookService;

    public BookResource(BookService bookService, @Legacy BookService legacyBookService) {
        this.bookService = bookService;
        this.legacyBookService = legacyBookService;
    }

    @GET
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook() {
        Book book  = bookService.create("Test title", 9.99F, "Test book");
        return Response.ok(book).build();
    }

    @GET
    @Path("/add/legacy")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLegacyBook() {
        Book book = legacyBookService.create("Legacy Test title", 9.99F, "Legacy book");
        return Response.ok(book).build();
    }

    @GET
    @Path("/remove")
    @Produces(MediaType.TEXT_PLAIN)
    public Response removeBook(@QueryParam("isbn") String isbn) {
        bookService.remove(isbn);
        return Response.ok().build();
    }
}
