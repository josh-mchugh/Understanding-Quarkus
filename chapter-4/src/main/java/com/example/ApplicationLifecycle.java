package com.example;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class ApplicationLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationLifecycle.class);

    void onStart(@Observes StartupEvent event) {
        LOGGER.info("The application is starting...");
    }

    public void onStop(@Observes ShutdownEvent event) {
        LOGGER.info("The application is shutting down...");
    }
}
