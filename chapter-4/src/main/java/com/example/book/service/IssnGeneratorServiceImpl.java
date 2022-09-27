package com.example.book.service;

import com.example.book.service.annotation.EightDigits;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@EightDigits
@ApplicationScoped
public class IssnGeneratorServiceImpl implements NumberGenerator {

    @Override
    public String generateNumber() {

        return String.format("8-%d", Math.abs(new Random().nextInt()));
    }
}
