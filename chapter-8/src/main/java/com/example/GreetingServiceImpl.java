package com.example;

import java.util.Map;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class GreetingServiceImpl implements GreetingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingServiceImpl.class);

    private final Map<String, String> GREETINGS = Map.of(
        "English", "Hello",
        "Spanish", "hola",
        "French", "bonjour",
        "German", "guten tag",
        "Italian", "salve",
        "Chinese", "nǐn hǎo",
        "Portuguese", "olá",
        "Arabic", "asalaam alaikum",
        "Japanese", "konnichiwa",
        "Korean", "anyoung haseyo"
    );

    @Override
    @Incoming("greeing-request")
    public CompletionStage<Void> greeting(Message<GreetingRequest> message) {
       
        GreetingRequest request = message.getPayload();

        LOGGER.info("{} {}", getLanguage(request), getName(request));

        return message.ack();
    }

    private String getLanguage(GreetingRequest request) {

        if(request != null) {

            if(request.getLanguage() != null && GREETINGS.containsKey(request.getLanguage())) {
                return GREETINGS.get(request.getLanguage());
            }
        }

        return "English";
    }

    private String getName(GreetingRequest request) {

        if(request != null) {

            if(request.getName() != null || !request.getName().isEmpty()) {

                return request.getName();
            }
        }

        return "Anonymous";
    }
}
