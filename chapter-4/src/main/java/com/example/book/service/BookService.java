package com.example.book.service;

import com.example.book.model.Book;

public interface BookService {

    Book create(String title, Float price, String description);

    void remove(String isbn);
}
