package com.example.book.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {

    @Test
    public void whenBookHasTitleExpectTitle() {

        Book book = new Book("title", null, null);

        Assertions.assertEquals("title", book.getTitle());
    }

    @Test
    public void whenBookHasNullTitleExpectNull() {

        Book book = new Book(null, null, null);

        Assertions.assertNull(book.getTitle());
    }

    @Test
    public void whenBookHasPriceExpectPrice() {

        Book book = new Book(null, 0.01F, null);

        Assertions.assertEquals(0.01F, book.getPrice());
    }

    @Test
    public void whenBookHasNullPriceExpectPrice() {

        Book book = new Book(null, null, null);

        Assertions.assertNull(book.getPrice());
    }

    @Test
    public void whenBookHasDescriptionExpectDescription() {

        Book book = new Book(null, null, "description");

        Assertions.assertEquals("description", book.getDescription());
    }

    @Test
    public void whenBookHasNullDescriptionThenExpectNull() {

        Book book = new Book(null, null, null);

        Assertions.assertNull(book.getDescription());
    }

    @Test
    public void whenBookHasValidConstructorExpectNullISBN() {

        Book book = new Book("title", 0.001F, "description");

        Assertions.assertNull(book.getIsbn());
    }

    @Test
    public void whenBookHasISBNThenExpectISBN() {

        Book book = new Book(null, null, null);
        book.setIsbn("isbn");

        Assertions.assertEquals("isbn", book.getIsbn());
    }
}
