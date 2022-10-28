package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsbnNumbersTest {
    
    @Test
    public void whenNumbersHasIsbn10ExpectIsbn10() {

        IsbnNumbers numbers = new IsbnNumbers().setIsbn10("1234");

        Assertions.assertEquals("1234", numbers.getIsbn10());
    }

    @Test
    public void whenNumbersHasIsbn13ExpectIsbn13() {

        IsbnNumbers numbers = new IsbnNumbers().setIsbn13("1234");

        Assertions.assertEquals("1234", numbers.getIsbn13());
    }
}
