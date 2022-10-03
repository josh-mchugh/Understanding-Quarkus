package com.example;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Publisher extends PanacheEntity {

    @Column(name = "name", length = 30)
    public String name;

    public static Optional<Publisher> findByName(String name) {

        return find("name", name).firstResultOptional();
    }

    public static long deleteByName(String name) {

        return delete("name", name);
    }
}
