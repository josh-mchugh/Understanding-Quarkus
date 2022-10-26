package com.exmaple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AuthorTest {

    @Test
    public void whenAuthorHasFirstNameThenExpectFirstName() {

        Author author = new Author();
        author.firstName = "firstName";

        Assertions.assertEquals("firstName", author.firstName);
    }

    @Test
    public void whenAuthorHasLastNameThenExpectLastName() {

        Author author = new Author();
        author.lastName = "lastName";

        Assertions.assertEquals("lastName", author.lastName);
    }
    
}
