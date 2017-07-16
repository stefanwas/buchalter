package com.stefan.buchalter.domain.model.report;

public interface Report {

    Long getId();
    String getCode();

    // income
    double getTotalNetIncome();
    double getTotalVatIncome();
    double getTotalGrossIncome();
    double getTotalPitIncome();

    // expense
    double getTotalNetExpense();
    double getTotalVatExpense();
    double getTotalGrossExpense();
    double getTotalPitExpense();
    double getTotalVatToDeduct();
}
