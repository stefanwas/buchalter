package com.stefan.buchalter.persistance.model;

public class PersistentReport {

    public enum Type {
        MONTHLY,
        QUARTERLY,
        ANNUAL}

    private Long id;
    private String code;
    private Type type;
    private int year;
    private int quarter;
    private int month;
    private Long parentReportId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Long getParentReportId() {
        return parentReportId;
    }

    public void setParentReportId(Long parentReportId) {
        this.parentReportId = parentReportId;
    }
}
