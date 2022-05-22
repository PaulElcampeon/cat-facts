package com.facts.catfacts.models;

public class Fact {

    private Long id;
    private String fact;

    private Rating rating;

    public Fact(String fact) {
        this.fact = fact;
        this.rating = new Rating();
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

    public Rating getRating() {
        return rating;
    }
}
