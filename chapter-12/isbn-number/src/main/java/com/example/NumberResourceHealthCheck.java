package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class NumberResourceHealthCheck implements HealthCheck {

    private final IsbnResource isbnResource;

    public NumberResourceHealthCheck(IsbnResource isbnResource) {
        this.isbnResource = isbnResource;
    }

    @Override
    public HealthCheckResponse call() {
        isbnResource.getIsbnNumbers();
        return HealthCheckResponse.named("Ping Number REST Endpoint.").up().build();
    }
}
