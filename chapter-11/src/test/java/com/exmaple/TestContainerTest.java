package com.exmaple;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;

@QuarkusTest
//@TestProfile(PostgresProfile.class)
public class TestContainerTest {
    
    public static PostgreSQLContainer<?> pg = new PostgreSQLContainer<>("postgres:14.5")
        .withDatabaseName("test-db")
        .withUsername("postgres")
        .withPassword("postgres")
        .withExposedPorts();

    @Test
    public void whenAuthorExistsThenExpectAuthor() {

        Author expected = new Author();

        AuthorService service = new AuthorServiceImpl();

        service.persist(expected);

        Optional<Author> result = service.findById(expected.id);
        
        Assertions.assertFalse(result.isPresent());
    }
}
