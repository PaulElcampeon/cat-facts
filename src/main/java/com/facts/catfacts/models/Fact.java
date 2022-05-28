package com.facts.catfacts.models;


import javax.persistence.*;

@Entity
@Table(name = "facts")
public class Fact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fact;
    private Long rating;

    public Fact() {
    }

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

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", fact='" + fact + '\'' +
                ", rating=" + rating +
                '}';
    }
}
