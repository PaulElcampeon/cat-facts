package com.facts.catfacts.utils;

import java.io.IOException;
import java.util.List;

public interface CustomReadable<T> {

    List<T> readFileReturnList(String path) throws IOException;
}
