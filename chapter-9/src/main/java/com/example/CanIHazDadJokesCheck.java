package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import io.smallrye.health.checks.UrlHealthCheck;

@Liveness
@ApplicationScoped
public class CanIHazDadJokesCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {

        return new UrlHealthCheck("https://icanhazdadjoke.com/").name("Can I Haz Dad Joke").call();
    }
}
