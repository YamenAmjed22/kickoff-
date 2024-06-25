package com.yu.kickoff.model;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @SequenceGenerator(
            name= "id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id"
    )
    private Long id ;
    private String name ;
    //relation with pitch //
    // relation with contacts //
    // relation with user //
    // no para const //
    public City() {
    }
    // all para const //

    public City(String name) {
        this.name = name;
    }
    // setter and getter //

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
