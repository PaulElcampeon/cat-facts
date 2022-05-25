package com.facts.catfacts.services;

import com.facts.catfacts.models.Fact;

import java.util.List;
import java.util.Optional;

public interface FactService {

    Optional<Fact> getFact(Long id);

    Fact getRandomFact();

    List<Fact> getAll();

    Fact addFact(Fact newFact);

    void removeFact(Long id);

    long count();

    long getRandomNumber();

}
