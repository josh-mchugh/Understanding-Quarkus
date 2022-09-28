package com.example.book.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssnGeneratorServiceTest {

    @Test
    public void wheGenerateNumberExpectStartWith() {

        NumberGenerator service = new IssnGeneratorServiceImpl();

        Assertions.assertTrue(service.generateNumber().startsWith("8-"));
    }
}
