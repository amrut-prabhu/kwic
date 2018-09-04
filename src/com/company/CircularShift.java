package com.company;

import java.util.ArrayList;
import java.util.List;

public class CircularShift {
    private Line line;
    private Integer offset;

    public CircularShift(Line line, int offset) {
        this.line = line;
        this.offset = offset;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int numberOfWords = line.getNumberOfWords();
        int i = 0;
        while (i < numberOfWords) {
            builder.append(line.getWord((offset + i) % numberOfWords)).append(" ");
            i++;
        }
        return builder.toString();
    }
}
