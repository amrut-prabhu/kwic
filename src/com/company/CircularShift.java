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
        int thisCount = 0;
        int otherCount = 0;
        int thisIndex = this.offset;
        int otherIndex = other.offset;

        while (thisCount < this.line.getNumberOfWords() && otherCount < other.line.getNumberOfWords()) {

            int compareVal = this.line.getWord(thisIndex).compareToIgnoreCase(other.line.getWord(otherIndex));
            
            if (compareVal > 0) {
                return 1;
            } else if (compareVal < 0) {
                return -1;
            }
            
            thisCount++;
            otherCount++;
            thisIndex = (thisIndex + 1) % this.line.getNumberOfWords();
            otherIndex = (otherIndex + 1) % other.line.getNumberOfWords();
        }

        if (thisCount >= this.line.getNumberOfWords()) {
            return -1;
        } else if (otherCount >= other.line.getNumberOfWords()) {
            return 1;
        } else {
            return 0;
        }
    }
}
