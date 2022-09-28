package com.example.book.service;

import com.example.book.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.enterprise.event.Event;

public class BookServiceTest {

    private Event<Book> addBookEvent;
    private Event<String> removeBookEvent;

    @BeforeEach
    public void setup() {
        addBookEvent = (Event<Book>) Mockito.mock(Event.class);
        removeBookEvent = (Event<String>) Mockito.mock(Event.class);
    }

    @Test
    public void whenCreatedExpectTitle() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        Book result = service.create("title", 0.01F, "description");

        Assertions.assertEquals("title", result.getTitle());
    }

    @Test
    public void whenCreateExpectPrice() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        Book result = service.create("title", 0.01F, "description");

        Assertions.assertEquals(0.01F, result.getPrice());
    }

    @Test
    public void whenCreateExpectDescription() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        Book result = service.create("title", 0.01F, "description");

        Assertions.assertEquals("description", result.getDescription());
    }

    @Test
    public void whenCreateExpectIsbnNotNull() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        Book result = service.create("title", 0.01F, "description");

        Assertions.assertNotNull(result.getIsbn());
    }

    @Test
    public void whenCreateWithNullParamsExpectBook() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        
        Assertions.assertNotNull(service.create(null, null, null));
    }

    @Test
    public void whenCreateExpectAddBookEventCalledOnce() {

        Mockito.spy(addBookEvent);

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        service.create("title", 0.01F, "description");

        Mockito.verify(addBookEvent, Mockito.times(1)).fire(Mockito.any());
    }

    @Test
    public void whenCreateServiceHasIssnGeneratorServiceExpectIssnFormatISBN() {

        BookService service = new BookServiceImpl(new IsbnGeneratorServiceImpl(), addBookEvent, removeBookEvent);
        Book result = service.create("title", 0.01F, "description");

        Assertions.assertTrue(result.getIsbn().startsWith("13-84356-"));
    }
}
