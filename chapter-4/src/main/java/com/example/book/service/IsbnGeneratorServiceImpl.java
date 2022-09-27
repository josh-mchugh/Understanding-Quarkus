package com.example.book.service;

import com.example.book.service.annotation.ThirteenDigits;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;

@ThirteenDigits
@ApplicationScoped
public class IsbnGeneratorServiceImpl implements NumberGenerator {

    @Override
    public String generateNumber() {

        return String.format("13-84356-%d", Math.abs(new Random().nextInt()));
    }
}
