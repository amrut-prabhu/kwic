package com.company.adt.sorter;

import com.company.adt.input.Input;
import com.company.adt.shifter.CircularShift;
import com.company.adt.shifter.Shifter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KwicAlphabetizer implements Sorter {

    private final Shifter shifter;
    private List<CircularShift> sortedCircularShifts;

    public KwicAlphabetizer(Shifter shifter) {
        this.shifter = shifter;
    }

    public void generateSortedList() {
        sortedCircularShifts = shifter.getCircularShifts();
        Collections.sort(sortedCircularShifts);
    }

    public List<?> getSortedList() {
        return new ArrayList<>(this.sortedCircularShifts);
    }

}
