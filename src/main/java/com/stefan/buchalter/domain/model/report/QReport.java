package com.stefan.buchalter.domain.model.report;

import java.util.ArrayList;
import java.util.List;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQCode;


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
        this.code = createYQCode(year, quarter);
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

    public MReport getMReportByCode(String code) {
        return mReports.stream()
                .filter(mReport -> mReport.getCode().equals(code))
                .findFirst().orElse(null);
    }

    public void addMReport(MReport mReport) {
        this.mReports.add(mReport);
    }

    public void addAllMReports(List<MReport> mReports) {
        this.mReports.addAll(mReports);
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
    public double calculateTotalNetIncome() {
        return mReports.stream().mapToDouble(MReport::calculateTotalNetIncome).sum();
    }

    @Override
    public double calculateTotalVatIncome() {
        return mReports.stream().mapToDouble(MReport::calculateTotalVatIncome).sum();
    }

    @Override
    public double calculateTotalGrossIncome() {
        return mReports.stream().mapToDouble(MReport::calculateTotalGrossIncome).sum();
    }

    @Override
    public double calculateTotalPitIncome() {
        return mReports.stream().mapToDouble(MReport::calculateTotalPitIncome).sum();
    }

    @Override
    public double calculateTotalNetExpense() {
        return mReports.stream().mapToDouble(MReport::calculateTotalNetExpense).sum();
    }

    @Override
    public double calculateTotalVatExpense() {
        return mReports.stream().mapToDouble(MReport::calculateTotalVatExpense).sum();
    }

    @Override
    public double calculateTotalGrossExpense() {
        return mReports.stream().mapToDouble(MReport::calculateTotalGrossExpense).sum();
    }

    @Override
    public double calculateTotalPitExpense() {
        return mReports.stream().mapToDouble(MReport::calculateTotalPitExpense).sum();
    }

    @Override
    public double calculateTotalVatToDeduct() {
        return mReports.stream().mapToDouble(MReport::calculateTotalVatToDeduct).sum();
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
