package com.facts.catfacts.controllers;

import com.facts.catfacts.models.Fact;
import com.facts.catfacts.services.FactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Fact getFactById(@PathVariable long id) {
        return factService.getFact(id).orElse(new Fact(""));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Fact addFact(Fact fact) {
        return factService.addFact(fact);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Fact updateFact(Fact fact) {
        return factService.updateFact(fact);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteFactById(@PathVariable long id) {
       factService.removeFact(id);
    }
}
