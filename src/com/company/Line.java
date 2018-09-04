package com.company;

import java.util.Arrays;
import java.util.List;

/**
 * Line consisting of a ist of words.
 */
public class Line {

    private List<String> words;

    public Line(String line) {
        words = Arrays.asList(line.split(" "));
    }

    public String getWord(int index) {
        return words.get(index);
    }

    public int getNumberOfWords() {
        return words.size();
    }
}
