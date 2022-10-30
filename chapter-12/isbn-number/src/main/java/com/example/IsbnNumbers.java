package com.example;

import javax.json.bind.annotation.JsonbProperty;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Several formats of book ISBN numbers")
public class IsbnNumbers {
 
    @Schema(required = true)
    @JsonbProperty("isbn_10")
    private String isbn10;

    @Schema(required = true)
    @JsonbProperty("isbn_13")
    private String isbn13;

    public String getIsbn10() {
        return isbn10;
    }

    public IsbnNumbers setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
        return this;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public IsbnNumbers setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
        return this;
    }
}
