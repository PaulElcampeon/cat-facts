package com.facts.catfacts.controllers;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("facts")
public class FactController {

    @Autowired
    private FactService factService;

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public Fact getRandomFact() {
        return factService.getRandomFact();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Fact> getFactById(@PathVariable long id) {
        return new ResponseEntity<>(factService.getFact(id).orElse(new Fact("")), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Fact> addFact(@RequestBody Fact fact) {
        return new ResponseEntity<>(factService.addFact(fact), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Fact> updateFact(@RequestBody Fact fact) {
        return new ResponseEntity<>(factService.updateFact(fact), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteFactById(@PathVariable long id) {
        try {
            factService.removeFact(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, e.getMessage(), e.getCause());
        }
       return new ResponseEntity<>(HttpStatus.OK);
    }
}
