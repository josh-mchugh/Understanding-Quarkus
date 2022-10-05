package com.example.publisher;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    
    List<Publisher> findAll();

    Optional<Publisher> findByName(String name);

    Optional<Publisher> findById(Long id);

    void persist(Publisher publisher);

    void deleteByName(String name);

    void deleteById(Long id);
}
