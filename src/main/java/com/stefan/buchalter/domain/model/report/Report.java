package com.stefan.buchalter.domain.model.report;

public interface Report {

    Long getId();
    String getCode();

    // income
    double calculateTotalNetIncome();
    double calculateTotalVatIncome();
    double calculateTotalGrossIncome();
    double calculateTotalPitIncome();

    // expense
    double calculateTotalNetExpense();
    double calculateTotalVatExpense();
    double calculateTotalGrossExpense();
    double calculateTotalPitExpense();
    double calculateTotalVatToDeduct();
}
