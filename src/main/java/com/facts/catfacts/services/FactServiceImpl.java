package com.facts.catfacts.services;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FactServiceImpl implements FactService {

    @Autowired
    private FactRepository repository;

    @Override
    public Optional<Fact> getFact(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Fact> getAll() {
        return repository.findAll();
    }

    @Override
    public Fact addFact(Fact newFact) {
        return repository.save(newFact);
    }

    @Override
    public void removeFact(Long id) {
        repository.deleteById(id);
    }
}
