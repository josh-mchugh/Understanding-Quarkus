package com.example.book.service;

import com.example.book.model.Book;
import com.example.book.service.annotation.AddBook;
import com.example.book.service.annotation.RemoveBook;
import com.example.book.service.annotation.ThirteenDigits;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;

@ApplicationScoped
public class BookServiceImpl implements BookService {

    private final NumberGenerator numberGenerator;
    private final Event<Book> addBookEvent;
    private final Event<String> removeBookEvent;

    public BookServiceImpl(
            @ThirteenDigits NumberGenerator numberGenerator,
            @AddBook Event<Book> addBookEvent,
            @RemoveBook Event<String> removeBookEvent
    ) {
        this.numberGenerator = numberGenerator;
        this.addBookEvent = addBookEvent;
        this.removeBookEvent = removeBookEvent;
    }

    @Override
    public Book create(String title, Float price, String description) {

        Book book = new Book(title, price, description);
        book.setIsbn(numberGenerator.generateNumber());
        addBookEvent.fire(book);

        return book;
    }

    @Override
    public void remove(String isbn) {

        removeBookEvent.fire(isbn);
    }
}
