package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class AmIReadyCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        
        return HealthCheckResponse.named("Ready or not... here we go").up().build();
    }
}
