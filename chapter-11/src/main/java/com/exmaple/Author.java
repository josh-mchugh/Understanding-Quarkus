package com.exmaple;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Table(name = "authors")
public class Author extends PanacheEntity {
    
    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;
}
