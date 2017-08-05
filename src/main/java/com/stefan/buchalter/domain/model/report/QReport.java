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
