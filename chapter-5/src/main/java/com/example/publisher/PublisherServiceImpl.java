package com.example.publisher;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class PublisherServiceImpl implements PublisherService {

    @Override
    public List<Publisher> findAll() {

        return Publisher.findAll().list();
    }

    @Override
    public Optional<Publisher> findByName(String name) {

        return Publisher.findByName(name);
    }

    @Override
    public Optional<Publisher> findById(Long id) {
        
        return Publisher.findByIdOptional(id);
    }

    @Override
    public void persist(Publisher publisher) {
        
        publisher.persist();
    }

    @Override
    public void deleteByName(String name) {

        Publisher.deleteByName(name);
    }

    @Override
    public void deleteById(Long id) {
        
        Publisher.deleteById(id);
    }
}
