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
}
