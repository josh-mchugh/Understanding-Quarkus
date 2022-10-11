package com.example.publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@Transactional
public class PublisherServiceTest {
 
    @BeforeEach
    public void setup() {
        Publisher.deleteAll();
    }

    @Test
    public void whenFindAllHasNoResults() {

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertEquals(new ArrayList<>(), service.findAll());
    }

    @Test
    public void whenFindAllHasResultsExpectResults() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        List<Publisher> result = service.findAll();

        Assertions.assertEquals(List.of(publisher), result);        
    }

    @Test
    public void whenFindByNameHasNoResultsExpectEmptyOptional() {

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertEquals(Optional.empty(), service.findByName("name"));
    }

    @Test
    public void whenFindByNameHasResultExpectResult() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertEquals(Optional.of(publisher), service.findByName("name"));
    }

    @Test
    public void whenFindByIdHasNoResultsExpectEmptyOptional() {

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertEquals(Optional.empty(), service.findById(1L));
    }

    @Test
    public void whenFindByIdHasResultsExpectResults() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertEquals(Optional.of(publisher), service.findById(publisher.id));
    }

    @Test
    public void whenPersistHasValidParamExpectNoException() {

        Publisher publisher = createPublisher();

        PublisherService service = new PublisherServiceImpl();
    
        Assertions.assertDoesNotThrow(() -> service.persist(publisher));
    }

    @Test
    public void whenPersistHasNullParamExpectException() {
        
        PublisherService service = new PublisherServiceImpl();
        
        Assertions.assertThrows(NullPointerException.class, () -> service.persist(null));
    }

    @Test
    public void whenPersistExpectId() {

        Publisher publisher = createPublisher();
        
        PublisherService service = new PublisherServiceImpl();

        service.persist(publisher);

        Assertions.assertNotNull(publisher.id);
    }

    @Test
    public void whenDeleteByNameHasValidParamThenExpectNoException() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertDoesNotThrow(() -> service.deleteByName(publisher.name));
    }

    @Test
    public void whenDeleteByNameHasNullParamThenExpectNoException() {

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertDoesNotThrow(() -> service.deleteByName(null));
    }

    @Test
    public void whenDeleteByNameThenExpectRemoved() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        service.deleteByName(publisher.name);

        Assertions.assertEquals(List.of(), service.findAll());
    }

    @Test
    public void whenDeleteByIdHasValidParamThenExpectNoExceptions() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertDoesNotThrow(() -> service.deleteById(publisher.id));
    }

    @Test
    public void whenDeleteByIdHasNullParamThenExpectIllegalArgumentsException() {

        PublisherService service = new PublisherServiceImpl();

        Assertions.assertThrows(IllegalArgumentException.class, () -> service.deleteById(null));
    }

    @Test
    public void whenDeleteByIdThenExpectRemovedResults() {

        Publisher publisher = createPublisher();
        publisher.persist();

        PublisherService service = new PublisherServiceImpl();

        service.deleteById(publisher.id);

        Assertions.assertEquals(List.of(), service.findAll());
    }

    private Publisher createPublisher() {

        return createPublisher("name");
    }

    private Publisher createPublisher(String name) {

        Publisher publisher = new Publisher();
        publisher.name = name;

        return publisher;
    }
}
