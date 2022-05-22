package com.facts.catfacts.repositories;

import com.facts.catfacts.models.Fact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactRepository extends MongoRepository<Fact, Long> {
}
