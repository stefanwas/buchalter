package com.stefan.buchalter.domain.report;

import java.util.ArrayList;
import java.util.List;


/**
 * Monthly Report
 */
public class QReport implements Report {

    private Long id;
    private final String code; // example: R2017Q1...R2017Q4
    private final int year;
    private final int quarter;

    private List<MReport> mReports = new ArrayList<>(3);

    public QReport(int year, int quarter) {
        this.year = year;
        this.quarter = quarter;
        this.code = "R" + year + "Q" + quarter;

//        for (int month = 1; month <= 3; month++) {
//            mReports.add(new MReport(year, (quarter - 1) * 3 + month));
//        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MReport> getMReports() {
        return mReports;
    }

    public void addMReport(MReport mReport) {
        mReports.add(mReport);
    }

    public void removeMReport(String code) {
        mReports.removeIf(mReport -> mReport.getCode().equals(code));
    }

    public int getYear() {
        return year;
    }

    public int getQuarter() {
        return quarter;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public double getTotalNetIncome() {
        return mReports.stream().mapToDouble(MReport::getTotalNetIncome).sum();
    }

    @Override
    public double getTotalVatIncome() {
        return mReports.stream().mapToDouble(MReport::getTotalVatIncome).sum();
    }

    @Override
    public double getTotalGrossIncome() {
        return mReports.stream().mapToDouble(MReport::getTotalGrossIncome).sum();
    }

    @Override
    public double getTotalPitIncome() {
        return mReports.stream().mapToDouble(MReport::getTotalPitIncome).sum();
    }

    @Override
    public double getTotalNetExpense() {
        return mReports.stream().mapToDouble(MReport::getTotalNetExpense).sum();
    }

    @Override
    public double getTotalVatExpense() {
        return mReports.stream().mapToDouble(MReport::getTotalVatExpense).sum();
    }

    @Override
    public double getTotalGrossExpense() {
        return mReports.stream().mapToDouble(MReport::getTotalGrossExpense).sum();
    }

    @Override
    public double getTotalPitExpense() {
        return mReports.stream().mapToDouble(MReport::getTotalPitExpense).sum();
    }

    @Override
    public double getTotalVatToDeduct() {
        return mReports.stream().mapToDouble(MReport::getTotalVatToDeduct).sum();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(">> QReport [").append(code).append("]\n");
        result.append(">> ------------------------\n");
        mReports.forEach(result::append);
        result.append("\n");
        return result.toString();
    }
}
