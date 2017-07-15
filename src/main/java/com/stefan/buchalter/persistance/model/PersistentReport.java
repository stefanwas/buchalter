package com.stefan.buchalter.persistance.model;

public class PersistentReport {

    private Long id;
    private Long yReportId;
    private Long qReportId;
    private String type;
    private String code;
    private int year;
    private int quarter;
    private int month;
//    private Long parentReportId;

    public Long getId() {
        return id;
    }

    public Long getYReportId() {
        return yReportId;
    }

    public void setYReportId(Long yReportId) {
        this.yReportId = yReportId;
    }

    public Long getQReportId() {
        return qReportId;
    }

    public void setQReportId(Long qReportId) {
        this.qReportId = qReportId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
//
//    public Long getParentReportId() {
//        return parentReportId;
//    }
//
//    public void setParentReportId(Long parentReportId) {
//        this.parentReportId = parentReportId;
//    }
}
