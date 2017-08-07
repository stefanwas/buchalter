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
        result.append("YReport [").append(code).append("]\n");
        result.append("---------------------------\n");
        qReports.forEach(result::append);
        result.append("\n");
        return result.toString();
    }
}
