package com.facts.catfacts.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facts")
public class Fact {
    @Id
    private Long id;
    private String fact;
    private Long rating;

    public Fact(String fact) {
        this.fact = fact;
    }

    public Long getId() {
        return id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Long getRating() {
        return rating;
    }
}
