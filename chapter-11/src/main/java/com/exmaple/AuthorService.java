package com.exmaple;

import java.util.Optional;

public interface AuthorService {
    
    void persist(Author author);

    Optional<Author> findById(Long id);
}
