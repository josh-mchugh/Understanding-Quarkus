package com.example.book.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.book.model.Book;

public class InventoryServiceTest {

    @Test
    public void whenAddBookHasValidParamExpectNoException() {
        
        Book book = new Book("title", 0.01F, "description");

        InventoryService service = new InventoryService();

        Assertions.assertDoesNotThrow(() -> service.addBook(book));
    }

    @Test
    public void whenAddBookHasNullParamExpectException() {

        InventoryService service = new InventoryService();

        Assertions.assertThrows(NullPointerException.class, () -> service.addBook(null));
    }

    @Test
    public void whenRemoveBookHasValidParamExpectNoException() {

        Book book = new Book("title", 0.01F, "description");
        book.setIsbn("123");

        InventoryService service = new InventoryService();
        service.addBook(book);

        Assertions.assertDoesNotThrow(() -> service.removeBook(book.getIsbn()));
    }

    @Test
    public void whenRemoveBookHasInvalidParamExpectNoException() {


        InventoryService service = new InventoryService();

        Assertions.assertDoesNotThrow(() -> service.removeBook("1234"));
    }

    @Test
    public void whenRemoveBookHasNullParamExpectNoException() {

        InventoryService service = new InventoryService();

        Assertions.assertDoesNotThrow(() -> service.removeBook(null));
    }
}
