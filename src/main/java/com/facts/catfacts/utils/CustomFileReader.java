package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CustomFileReader implements CustomReadable<Fact> {

    @Autowired
    private Function<String, Fact> mapper;

    @Override
    public List<Fact> readFileReturnList(String path) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            return in.lines()
                    .map(mapper)
                    .collect(Collectors.toList());
        }
    }
}
