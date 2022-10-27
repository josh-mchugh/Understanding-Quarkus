package com.exmaple;

import java.util.Map;

import io.quarkus.test.junit.QuarkusTestProfile;

public class PostgresProfile implements QuarkusTestProfile {
    
    @Override
    public Map<String, String> getConfigOverrides() {
        
        return Map.of(
            "quarkus.datasource.db-kind", "postgresql",
            "quarkus.datasource.jdbc.url", "jdbc:postgresql://localhost:5432/test-db"
        );
    }
}
