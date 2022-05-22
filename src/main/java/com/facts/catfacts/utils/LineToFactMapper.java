package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;

import java.util.function.Function;

public class LineToFactMapper implements Function<String, Fact> {
    @Override
    public Fact apply(String s) {
        return new Fact(s);
    }
}
