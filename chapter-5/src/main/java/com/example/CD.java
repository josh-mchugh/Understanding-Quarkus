package com.example;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CD extends Item {
    
    @Column(name = "total_duraction")
    public Float totalDuration;

    @Column(name = "music_company")
    public String musicCompany;

    @Column(name = "genre")
    public String genre;
}
