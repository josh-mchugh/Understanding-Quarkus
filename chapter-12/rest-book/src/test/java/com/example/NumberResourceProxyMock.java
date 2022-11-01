package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import com.example.numbers.IsbnNumbers;
import com.example.numbers.NumberResourceProxy;

import io.quarkus.test.Mock;

@Mock
@ApplicationScoped
@RestClient
public class NumberResourceProxyMock implements NumberResourceProxy {

    @Override
    public IsbnNumbers generateIsbnNumbers() {
        
        IsbnNumbers numbers = new IsbnNumbers();
        numbers.setIsbn10("dummy isbn 10");
        numbers.setIsbn13("dummy isbn 13");

        return numbers;
    }
    
}
