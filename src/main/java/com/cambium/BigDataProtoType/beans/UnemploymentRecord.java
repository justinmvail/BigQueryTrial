package com.cambium.BigDataProtoType.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.Year;

@Entity
public class UnemploymentRecord {

    @Id
    private String seriesId;
    private Year year;
    private String period;
    private Float value;
    private String footnote_codes;
    private LocalDate date;
    private String seriesTitle;

    public UnemploymentRecord(String seriesId, Year year, String period, Float value, String footnote_codes, LocalDate date, String seriesTitle) {
        this.seriesId = seriesId;
        this.year = year;
        this.period = period;
        this.value = value;
        this.footnote_codes = footnote_codes;
        this.date = date;
        this.seriesTitle = seriesTitle;
    }

    public UnemploymentRecord() {
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getFootnote_codes() {
        return footnote_codes;
    }

    public void setFootnote_codes(String footnote_codes) {
        this.footnote_codes = footnote_codes;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }
}
