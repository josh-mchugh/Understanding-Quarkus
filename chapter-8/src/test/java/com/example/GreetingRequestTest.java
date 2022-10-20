package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreetingRequestTest {
    
    @Test
    public void whenModelHasLanguageThenExpectLanguage() {

        GreetingRequest model = new GreetingRequest("English", "Tester");

        Assertions.assertEquals("English", model.getLanguage());
    }

    @Test
    public void whenModelHasNameThenExpectName() {

        GreetingRequest model = new GreetingRequest("English", "Tester");

        Assertions.assertEquals("Tester", model.getName());
    }
}
