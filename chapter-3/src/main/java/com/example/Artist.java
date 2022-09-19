package com.example;

import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import java.util.UUID;

@RegisterForReflection
public class Artist {

    @JsonbTransient
    private UUID id;
    @JsonbProperty("first_name")
    private String firstName;
    @JsonbProperty("last_name")
    private String lastName;

    public UUID getId() {
        return id;
    }

    public Artist id(UUID id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Artist firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Artist lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
