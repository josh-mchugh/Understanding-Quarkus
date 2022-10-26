package com.exmaple;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuthorServiceImpl implements AuthorService {

    @Override
    public void persist(Author author) {
        
        author.persist();
    }

    @Override
    public Optional<Author> findById(Long id) {
        
        return Author.findByIdOptional(id);
    }
    
}
