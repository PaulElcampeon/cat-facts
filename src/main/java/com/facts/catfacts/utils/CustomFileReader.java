package com.facts.catfacts.utils;

import com.facts.catfacts.models.Fact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFileReader {

    public static List<Fact> readFactsFile(String path) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            return in.lines()
                    .map(new LineToFactMapper())
                    .collect(Collectors.toList());
        }
    }
}
