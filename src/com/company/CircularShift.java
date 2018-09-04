package com.company;

import java.util.ArrayList;
import java.util.List;

public class CircularShift implements Comparable<CircularShift> {
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

    @Override
    public int compareTo(CircularShift other) {
        // If this offset is the last word
        if (this.offset == this.line.getNumberOfWords() - 1) {
            return -1;
        }

        // If other offset is the last word
        if (other.offset == other.line.getNumberOfWords() - 1) {
            return 1;
        }

        return this.line.getWord(this.offset).compareTo(other.line.getWord(other.offset));
    }
}
