package com.facts.catfacts.models;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "facts")
public class Fact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fact;
    
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

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", fact='" + fact + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fact fact1 = (Fact) o;
        return Objects.equals(id, fact1.id) && Objects.equals(fact, fact1.fact);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fact);
    }
}
