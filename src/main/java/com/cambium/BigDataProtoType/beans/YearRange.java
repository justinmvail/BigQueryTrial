package com.cambium.BigDataProtoType.beans;

import java.time.Year;

public class YearRange {
    private Year startingYear;
    private Year endingYear;

    public YearRange() {}

    public YearRange(Year startingYear, Year endingYear) {
        this.startingYear = startingYear;
        this.endingYear = endingYear;
    }

    public Year getStartingYear() {
        return startingYear;
    }

    public void setStartingYear(Year startingYear) {
        this.startingYear = startingYear;
    }

    public Year getEndingYear() {
        return endingYear;
    }

    public void setEndingYear(Year endingYear) {
        this.endingYear = endingYear;
    }
}
