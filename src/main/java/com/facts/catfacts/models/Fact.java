package com.facts.catfacts.models;

public class Fact {

    private Long id;
    private String fact;
    private Long ratingId;

    public Fact(String fact, Long ratingId) {
        this.fact = fact;
        this.ratingId = ratingId;
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

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }
}
