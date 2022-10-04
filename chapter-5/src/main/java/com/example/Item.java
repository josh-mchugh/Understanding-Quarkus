package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item extends PanacheEntity {

    @Column(name = "title", length = 100)
    public String title;
    
    @Column(name = "description", length = 3000)
    public String description;

    @Column(name = "unit_cost")
    public String unitCost;
}
