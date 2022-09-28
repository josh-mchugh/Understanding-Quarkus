package com.example.book.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IsbnGeneratorServiceTest {

    @Test
    public void whenGenerateNumberExpectFormatStartWith() {

        NumberGenerator service = new IsbnGeneratorServiceImpl();

        Assertions.assertTrue(service.generateNumber().startsWith("13-84356-"));
    }
}
