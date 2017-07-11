package com.stefan.buchalter.domain.report;

import com.stefan.buchalter.domain.record.Record;

import java.util.ArrayList;
import java.util.List;

/**
 * Monthly Report
 */
public class MReport implements Report {

    private Long id;
    private final String code; //R2017M10
    private final int year;
    private final int month;
    private final List<Record> incomeRecords = new ArrayList<>();
    private final List<Record> expenseRecords = new ArrayList<>();

    public MReport(int year, int month) {
        this.year = year;
        this.month = month;
        this.code = "R" + year + "M" + month;
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

    public void removeIncomeRecord(long id) {
        incomeRecords.removeIf(record -> record.getId().equals(id));
    }

    public void removeAllIncomeRecords() {
        incomeRecords.clear();
    }

    public void addExpenseRecord(Record record) {
        expenseRecords.add(record);
    }

    public void removeExpenseRecord(long id) {
        expenseRecords.removeIf(record -> record.getId().equals(id));
    }

    public void removeAllExpenseRecords() {
        expenseRecords.clear();
    }

    @Override
    public String getCode() {
        return code;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    @Override
    public double getTotalNetIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getNetValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalVatIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getVatValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalGrossIncome() {
        double total = incomeRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getGrossValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalPitIncome() {
        double total = incomeRecords.stream()
                .mapToDouble(record -> record.getPitValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalNetExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getNetValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalVatExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getVatValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalGrossExpense() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getGrossValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalVatToDeduct() {
        double total = expenseRecords.stream()
                .filter(record -> record.getType() == Record.Type.VAT)
                .mapToDouble(record -> record.getVatDeductionValue())
                .sum();
        return total;
    }

    @Override
    public double getTotalPitExpense() {
        double total = expenseRecords.stream()
                .mapToDouble(record -> record.getPitValue())
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
