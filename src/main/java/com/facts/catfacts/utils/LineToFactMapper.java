package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LineToFactMapper implements Function<String, Fact> {
    @Override
    public Fact apply(String s) {
        return new Fact(s);
    }
}
