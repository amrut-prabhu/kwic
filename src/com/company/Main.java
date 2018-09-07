package com.company;

import java.io.File;
import java.util.*;

/**
 * Master control class that creates all the objects and invokes all the methods needed to get the KWIC output.
 */
public class Main {

    public static final int FIRST_INDEX = 0;

    private List<Line> inputLines;
    private Set<String> wordsToIgnore;
    private List<CircularShift> circularShifts;
    private File inputFile;

    public Main() {
        inputLines = new ArrayList<>();
        wordsToIgnore = new HashSet<>();
        circularShifts = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.input(args);
        main.circularShift();
        main.alphabetize();
        main.output();
    }

    /**
     * Reads data lines from input text file and stores them for processing by other modules.
     */
    public void input(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("No input file specified.\n"
                    + "Usage: java com/company/Main <filename> [word to ignore]...");
        }

        inputFile = new File(args[FIRST_INDEX]);

        // Remaining args are the keywords that should be ignored.
        setWordsToIgnore(args);

        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLines.add(new Line(line));
        }
    }

    private void setWordsToIgnore(String[] args) {
        int numberOfArguments = args.length;
        if (numberOfArguments > 1) {
            for (int i = FIRST_INDEX + 1; i < numberOfArguments; i++) {
                wordsToIgnore.add(args[i].toLowerCase());
            }
        }
    }

    public void circularShift() {
        inputLines.forEach(line -> {
            int numberOfWords = line.getNumberOfWords();

            for (int wordIndex = 0; wordIndex < numberOfWords; wordIndex++) {
                String startWord = line.getWord(wordIndex);
                startWord = strip(startWord);
                if (!wordsToIgnore.contains(startWord.toLowerCase())) {
                    circularShifts.add(new CircularShift(line, wordIndex));
                }
            }
        });
    }

    /**
     * Remove first and/or last characters, if they are not letters or digits.
     * @param word String from which the characters are to be removed.
     * @return String which only consists of letters or digits.
     */
    private String strip(String word) {
        char firstChar = word.charAt(FIRST_INDEX);
        char lastChar = word.charAt(word.length() - 1);

        if (!Character.isDigit(firstChar) && !Character.isAlphabetic(firstChar)) {
            word = word.substring(FIRST_INDEX + 1);
        }

        if (word.length() > 0 && !Character.isDigit(lastChar) && !Character.isAlphabetic(lastChar)) {
            word = word.substring(FIRST_INDEX, word.length() - 1);
        }

        return word;
    }

    /**
     * Sorts all the circular shifts in lexicographical order.
     */
    public void alphabetize() {
        Collections.sort(circularShifts);
    }

    /**
     * Outputs the list of all valid circular shifts of all lines in lexicographical order.
     */
    public void output() {
        circularShifts.forEach(System.out::println);
    }
}