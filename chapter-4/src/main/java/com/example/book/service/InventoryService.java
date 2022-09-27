package com.example.book.service;

import com.example.book.model.Book;
import com.example.book.service.annotation.AddBook;
import com.example.book.service.annotation.RemoveBook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.event.Observes;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class InventoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);
    private final List<Book> inventory = new ArrayList<>();

    public void addBook(@Observes @AddBook Book book) {
        LOGGER.info("Adding book '{}' to inventory.", book.getIsbn());
        inventory.add(book);
    }

    public void removeBook(@Observes @RemoveBook String isbn) {
        LOGGER.info("Removing book '{}' from inventory", isbn);
        inventory.removeIf(book -> isbn.equals(book.getIsbn()));
    }
}
