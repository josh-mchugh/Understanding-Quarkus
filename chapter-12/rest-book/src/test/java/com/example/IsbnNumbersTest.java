package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.numbers.IsbnNumbers;

public class IsbnNumbersTest {

    @Test
    public void whenIsbNumbersHasValidIsn10ThenExpectIsbn10() {

        IsbnNumbers numbers = new IsbnNumbers();
        numbers.setIsbn10("isbn10");

        Assertions.assertEquals("isbn10", numbers.getIsbn10());
    }

    @Test
    public void whenIsbnNumbersHasValidIsbn13TheExpectIsbn13() {

        IsbnNumbers numbers = new IsbnNumbers();
        numbers.setIsbn13("isbn13");

        Assertions.assertEquals("isbn13", numbers.getIsbn13());
    }
    
}
