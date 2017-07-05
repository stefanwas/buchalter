package com.stefan.buchalter.domain.report;

import java.util.ArrayList;
import java.util.List;

/**
 * Annual Report
 */
public class AReport implements Report {

    private Long id;
    private final String code; // example: R2017
    private final int year;

    private final List<QReport> qReports = new ArrayList<>(4 );

    public AReport(int year) {
        this.year = year;
        this.code = "R" + year;

        for (int quarter = 1; quarter <= 4; quarter++) {
            qReports.add(new QReport(year, quarter));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public int getYear() {
        return year;
    }

    public List<QReport> getQReports() {
        return qReports;
    }

    public void addQReport(QReport qReport) {
        qReports.add(qReport);
    }

    public void removeQReport(String code) {
        qReports.removeIf(qReport -> qReport.getCode().equals(code));
    }

    @Override
    public double getTotalNetIncome() {
        return qReports.stream().mapToDouble(QReport::getTotalNetIncome).sum();
    }

    @Override
    public double getTotalVatIncome() {
        return qReports.stream().mapToDouble(QReport::getTotalVatIncome).sum();
    }

    @Override
    public double getTotalGrossIncome() {
        return qReports.stream().mapToDouble(QReport::getTotalGrossIncome).sum();
    }

    @Override
    public double getTotalPitIncome() {
        return qReports.stream().mapToDouble(QReport::getTotalPitIncome).sum();
    }

    @Override
    public double getTotalNetExpense() {
        return qReports.stream().mapToDouble(QReport::getTotalNetExpense).sum();
    }

    @Override
    public double getTotalVatExpense() {
        return qReports.stream().mapToDouble(QReport::getTotalVatExpense).sum();
    }

    @Override
    public double getTotalGrossExpense() {
        return qReports.stream().mapToDouble(QReport::getTotalGrossExpense).sum();
    }

    @Override
    public double getTotalPitExpense() {
        return qReports.stream().mapToDouble(QReport::getTotalPitExpense).sum();
    }

    @Override
    public double getTotalVatToDeduct() {
        return qReports.stream().mapToDouble(QReport::getTotalVatToDeduct).sum();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("AReport [").append(code).append("]\n");
        result.append("---------------------------\n");
        qReports.forEach(result::append);
        result.append("\n");
        return result.toString();
    }
}
