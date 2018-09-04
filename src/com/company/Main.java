package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    ArrayList<String> inputLines = new ArrayList<>();

    /**
     * Reads data lines from input medium and stores them for processing by other modules.
     */
    public void input(File inputFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(inputFile);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            inputLines.add(line);
        }
    }

    public void circularShift() {

    }

    /**
     * Sorter
     */
    public void alphabetize() {

    }

    /**
     * Outputs a listing of all circular shifts of all lines in alphabetical order.
     */
    public void output() {
        for (String s: inputLines) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Main main = new Main();

        File inputFile = new File(args[0]);

        main.input(inputFile);
        main.circularShift();
        main.alphabetize();
        main.output();
    }
}