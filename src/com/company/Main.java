package com.company;

import java.io.File;
import java.util.*;


public class Main {

    private List<Line> inputLines;
    private Set<String> wordsToIgnore;
    private File inputFile;
    private List<CircularShift> circularShifts;
//    private List<List<Integer>> circularShiftIndices;
//    private List<String> outputLines;

    public Main() {
        inputLines = new ArrayList<>();
        wordsToIgnore = new HashSet<>();
        circularShifts = new ArrayList<>();
//        circularShiftIndices = new ArrayList<>();
//        outputLines = new ArrayList<>();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.input(args);
        main.circularShift();
        main.alphabetize();
        main.output();
    }

    /**
     * Reads data lines from input medium and stores them for processing by other modules.
     */
    public void input(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("No file specified.\n" + "Usage: java com/company/Main <filename> <words to ignore>...");
        }

        inputFile = new File(args[0]);

        setWordsToIgnore(args);

        Scanner scanner = new Scanner(inputFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLines.add(new Line(line));
        }
    }

    private void setWordsToIgnore(String[] args) {
        if (args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                wordsToIgnore.add(args[i].toLowerCase());
            }
        }
    }

    public void circularShift() {
        for (Line line: inputLines) {
            for (int wordIndex = 0; wordIndex < line.getNumberOfWords(); wordIndex++) {
                String startWord = line.getWord(wordIndex);
                startWord = strip(startWord);
                if (!wordsToIgnore.contains(startWord.toLowerCase())) {
                    circularShifts.add(new CircularShift(line, wordIndex));
                }
            }
        }
    }

    /**
     * Remove first and/or last characters, if they are not letters or digits.
     * @param word
     * @return
     */
    private String strip(String word) {
        char firstChar = word.charAt(0);
        char lastChar = word.charAt(word.length() - 1);

        if (!Character.isDigit(firstChar) && !Character.isAlphabetic(firstChar)) {
            word = word.substring(1);
        }

        if (word.length() >= 2 && !Character.isDigit(lastChar) && !Character.isAlphabetic(lastChar)) {
            word = word.substring(0, word.length() - 2);
        }

        return word;
    }

    /**
     * Sorter.
     */
    public void alphabetize() {
        Collections.sort(circularShifts);
    }

    /**
     * Outputs a listing of all circular shifts of all lines in alphabetical order.
     */
    public void output() {
//        for (int lineIndex = 0; lineIndex < inputLines.size(); lineIndex++) {
//            String line = inputLines.get(lineIndex);
//            for (int startIndex: circularShiftIndices.get(lineIndex)){
//                System.out.println(line.substring(startIndex) + " " + line.substring(0, startIndex));
//            }
//        }
        for (CircularShift circularShift: circularShifts) {
            System.out.println(circularShift);
        }
    }
}