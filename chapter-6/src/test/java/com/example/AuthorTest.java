package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    
    @Test
    public void whenAuthorHasIdExpectId() {

        Author author = new Author().id(1);

        Assertions.assertEquals(1, author.getId());
    }

    @Test
    public void whenAuthorHasFirstNameExpectFirstName() {

        Author author = new Author().firstName("first");

        Assertions.assertEquals("first", author.getFirstName());
    }

    @Test
    public void whenAuthorHasLastNameThenExpectLastName() {

        Author author = new Author().lastName("last");

        Assertions.assertEquals("last", author.getLastName());
    }
}
