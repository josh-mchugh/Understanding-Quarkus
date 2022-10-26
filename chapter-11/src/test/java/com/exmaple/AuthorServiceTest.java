package com.exmaple;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Transactional
@QuarkusTestResource(H2DatabaseTestResource.class)
public class AuthorServiceTest {
    
    @Test
    public void whenValidAuthorExpectNoExceptions() {

        Author author = new Author();

        AuthorService service = new AuthorServiceImpl();

        Assertions.assertDoesNotThrow(() -> service.persist(author));
    }

    @Test
    public void whenValidAuthorExpectId() {

        Author author = new Author();

        AuthorService service = new AuthorServiceImpl();

        service.persist(author);

        Assertions.assertNotNull(author.id);
    }

    @Test
    public void whenAuthorHasFirstNameThenExpectFirstName() {

        Author author = new Author();
        author.firstName = "First Name";

        AuthorService service = new AuthorServiceImpl();

        service.persist(author);

        Assertions.assertEquals("First Name", author.firstName);
    }

    @Test
    public void whenAuthorHasLastNameThenExpectLastName() {

        Author author = new Author();
        author.lastName = "Last Name";

        AuthorService service = new AuthorServiceImpl();

        service.persist(author);

        Assertions.assertEquals("Last Name", author.lastName);
    }

    @Test
    public void whenAuthorIsNullThenExpectExcpetion() {

        AuthorService service = new AuthorServiceImpl();

        Assertions.assertThrows(NullPointerException.class, () -> service.persist((Author) null));
    }
    
    @Test
    public void whenFindByIdHasInvalidIdThenExpectNoResult() {

        AuthorService service = new AuthorServiceImpl();

        Optional<Author> result = service.findById(Long.MIN_VALUE);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void whenFindByIdHasValidIdThenExpectResult() {

        AuthorService service = new AuthorServiceImpl();

        Author expected = new Author();
        service.persist(expected);

        Optional<Author> result = service.findById(expected.id);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void whenFindByIdHasValidIdThenExpectId() {

        AuthorService service = new AuthorServiceImpl();

        Author expected = new Author();
        service.persist(expected);

        Optional<Author> result = service.findById(expected.id);

        Assertions.assertEquals(expected.id, result.get().id);
    }
}
