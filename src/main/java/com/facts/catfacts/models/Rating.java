package com.facts.catfacts.models;

public class Rating {
    private Long id;
    private Long count;
    private Long factId;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFactId() {
        return factId;
    }

    public void setFactId(Long factId) {
        this.factId = factId;
    }
}
