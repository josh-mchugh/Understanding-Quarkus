package com.example;

import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GreetingServiceTest {
    
    @Test
    public void whenMessageIsNullThenExpectException() {

        GreetingService service = new GreetingServiceImpl();

        Assertions.assertThrows(NullPointerException.class, () -> service.greeting(null));
    }

    @Test
    public void whenRequestIsNullExpectNoException() {

        GreetingService service = new GreetingServiceImpl();

        Assertions.assertDoesNotThrow(() -> service.greeting(Message.of((GreetingRequest) null)));
    }
}
