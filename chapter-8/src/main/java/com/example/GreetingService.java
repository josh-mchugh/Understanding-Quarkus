package com.example;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Message;

public interface GreetingService {
    
    CompletionStage<Void> greeting(Message<GreetingRequest> message);
}
