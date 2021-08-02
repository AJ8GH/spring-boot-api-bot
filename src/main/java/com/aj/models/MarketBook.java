package com.aj.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MarketBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public MarketBook() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
