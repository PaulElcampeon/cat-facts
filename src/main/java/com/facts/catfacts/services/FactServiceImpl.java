package com.facts.catfacts.services;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.repositories.FactRepository;
import com.facts.catfacts.utils.CustomFileReader;
import com.facts.catfacts.utils.CustomReadable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FactServiceImpl implements FactService {

    //    @Autowired
    private FactRepository repository;

    @Autowired
    public FactServiceImpl(CustomReadable<Fact> customFileReader, FactRepository repository) {
        this.repository = repository;
        try {
            List<Fact> facts = customFileReader.readFileReturnList("cat-facts.txt");
            facts.forEach(this::addFact);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Fact> getFact(Long id) {
        return repository.findById(id);
    }

    @Override
    public Fact getRandomFact() {
        return repository.findById(getRandomNumber()).get();
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
    public Fact updateFact(Fact updatedFact) {
        return repository.save(updatedFact);
    }

    @Override
    public void removeFact(Long id) {
        repository.deleteById(id);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long getRandomNumber() {
        long randomNumber = Math.round(Math.random() * count());
        return randomNumber < 0 ? 0 : randomNumber;
    }
}
