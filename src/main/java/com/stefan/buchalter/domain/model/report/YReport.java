package com.stefan.buchalter.domain.model.report;

import java.util.ArrayList;
import java.util.List;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYCode;

/**
 * Annual Report
 */
public class YReport implements Report {

    private Long id;
    private final String code; // example: R2017
    private final int year;

    private final List<QReport> qReports = new ArrayList<>(4);

    public YReport(int year) {
        this.year = year;
        this.code = createYCode(year);
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

    public QReport getQReportByCode(String code) {
        return qReports.stream()
                .filter(qReport -> qReport.getCode().equals(code))
                .findFirst().orElse(null);
    }

    public void addQReport(QReport qReport) {
        qReports.add(qReport);
    }

    public void addAllQReports(List<QReport> qReports) {
        this.qReports.addAll(qReports);
    }

    public void removeQReport(String code) {
        qReports.removeIf(qReport -> qReport.getCode().equals(code));
    }

    @Override
    public double calculateTotalNetIncome() {
        return qReports.stream().mapToDouble(QReport::calculateTotalNetIncome).sum();
    }

    @Override
    public double calculateTotalVatIncome() {
        return qReports.stream().mapToDouble(QReport::calculateTotalVatIncome).sum();
    }

    @Override
    public double calculateTotalGrossIncome() {
        return qReports.stream().mapToDouble(QReport::calculateTotalGrossIncome).sum();
    }

    @Override
    public double calculateTotalPitIncome() {
        return qReports.stream().mapToDouble(QReport::calculateTotalPitIncome).sum();
    }

    @Override
    public double calculateTotalNetExpense() {
        return qReports.stream().mapToDouble(QReport::calculateTotalNetExpense).sum();
    }

    @Override
    public double calculateTotalVatExpense() {
        return qReports.stream().mapToDouble(QReport::calculateTotalVatExpense).sum();
    }

    @Override
    public double calculateTotalGrossExpense() {
        return qReports.stream().mapToDouble(QReport::calculateTotalGrossExpense).sum();
    }

    @Override
    public double calculateTotalPitExpense() {
        return qReports.stream().mapToDouble(QReport::calculateTotalPitExpense).sum();
    }

    @Override
    public double calculateTotalVatToDeduct() {
        return qReports.stream().mapToDouble(QReport::calculateTotalVatToDeduct).sum();
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("YReport [").append(code).append("]\n");
        result.append("---------------------------\n");
        qReports.forEach(result::append);
        result.append("\n");
        return result.toString();
    }
}
