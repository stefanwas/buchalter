package com.stefan.buchalter.domain.model.report;

import com.stefan.buchalter.domain.model.record.Record;

import java.util.ArrayList;
import java.util.List;

import static com.stefan.buchalter.domain.model.ReportCodeUtil.createYQMCode;

/**
 * Monthly Report
 */
public class MReport implements Report {

    private Long id;
    private final String code; //R2017Q4M10
    private final int year;
    private final int quarter;
    private final int month;
    private final List<Record> incomeRecords = new ArrayList<>();
    private final List<Record> expenseRecords = new ArrayList<>();

    public MReport(int year, int quarter, int month) {
        this.year = year;
        this.quarter = quarter;
        this.month = month;
        this.code = createYQMCode(year, quarter, month);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addIncomeRecord(Record record) {
        incomeRecords.add(record);
    }

    public void addAllIncomeRecords(List<Record> records) {
        incomeRecords.addAll(records);
    }

    public void removeIncomeRecord(long id) {
        incomeRecords.removeIf(record -> record.getId().equals(id));
    }

    public void removeAllIncomeRecords() {
        incomeRecords.clear();
    }

    public List<Record> getIncomeRecords() {
        return incomeRecords;
    }

    public void addExpenseRecord(Record record) {
        expenseRecords.add(record);
    }

    public void addAllExpenseRecords(List<Record> records) {
        expenseRecords.addAll(records);
    }
    public void removeExpenseRecord(long id) {
        expenseRecords.removeIf(record -> record.getId().equals(id));
    }

    public void removeAllExpenseRecords() {
        expenseRecords.clear();
    }

    public List<Record> getExpenseRecords() {
        return expenseRecords;
    }

    @Override
    public String getCode() {
        return code;
    }

    public int getYear() {
        return year;
    }

    public int getQuarter() {
        return quarter;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public double calculateTotalNetIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getNetValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalVatIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getVatValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalGrossIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getGrossValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalPitIncome() {
        double total = incomeRecords.stream()
                .mapToDouble(Record::getPitValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalNetExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getNetValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalVatExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getVatValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalGrossExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getGrossValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalVatToDeduct() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(Record::getVatDeductionValue)
                .sum();
        return total;
    }

    @Override
    public double calculateTotalPitExpense() {
        double total = expenseRecords.stream()
                .mapToDouble(Record::getPitValue)
                .sum();
        return total;
    }


    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(">>>> MReport [").append(code).append("]\n");
        result.append(">>>> ----------------------\n");
        result.append("\n");
        return result.toString();
    }
}
