package com.example;

public class GreetingRequest {
    
    private String language;
    private String name;

    public GreetingRequest(String language, String name){
        this.language = language;
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }
}
