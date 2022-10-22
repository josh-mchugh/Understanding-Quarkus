package com.example;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class MicCheck implements HealthCheck{

    @Override
    public HealthCheckResponse call() {
        
        return HealthCheckResponse.named("Mic Check.. 1.. 2..").up().build();
    }
    
}
