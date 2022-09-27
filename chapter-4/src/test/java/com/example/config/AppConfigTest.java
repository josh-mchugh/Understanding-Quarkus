package com.example.config;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class AppConfigTest {

    @Inject
    AppConfig appConfig;

    @Test
    public void whenUrlHasValueThenExpectValue() {
        Assertions.assertEquals("www.test-url.com:1337", appConfig.url());
    }

    @Test
    public void whenUsernameHasValueThenExpectValue() {
        Assertions.assertEquals("not-root", appConfig.username());
    }

    @Test
    public void whenPasswordHasValueThenExpectValue() {
        Assertions.assertEquals("P@55W0RD", appConfig.password());
    }
}
