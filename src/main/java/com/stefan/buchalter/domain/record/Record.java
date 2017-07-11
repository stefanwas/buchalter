package com.stefan.buchalter.domain.record;

import java.time.LocalDate;

public class Record {

    public enum Type {
        VAT, PIT;
    }

    public enum VatRate {
        VAT_23(0.23), VAT_8(0.08), VAT_ZW(0.0);

        private double rate;

        VatRate(double rate) {
            this.rate = rate;
        }

        public double getRate() {
            return rate;
        }
    }

    private Long id;
    private String title;
    private LocalDate date;
    private Type type;
    private Double netValue;
    private VatRate vatRate;
    private Double vatValue;
    private Double grossValue;
    private double pitValue;
    private Double vatDeductionRate;
    private Double vatDeductionValue;

    public void recalculate() {
        if (type == Type.VAT) {
            vatValue = vatRate != null && vatRate != VatRate.VAT_ZW ? netValue * vatRate.getRate() : 0.0;
            grossValue = netValue + vatValue;
            vatDeductionValue = vatValue * vatDeductionRate;
            setPitValue(grossValue - vatDeductionValue);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPitValue() {
        return pitValue;
    }

    public void setPitValue(double pitValue) {
        this.pitValue = pitValue;
    }

    public double getNetValue() {
        return netValue;
    }

    public void setNetValue(double netValue) {
        this.netValue = netValue;
    }

    public VatRate getVatRate() {
        return vatRate;
    }

    public void setVatRate(VatRate vatRate) {
        this.vatRate = vatRate;
    }

    public double getVatDeductionRate() {
        return vatDeductionRate;
    }

    public void setVatDeductionRate(double vatDeductionRate) {
        this.vatDeductionRate = vatDeductionRate;
    }

    public double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }

    public double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }



    public double getVatDeductionValue() {
        return vatDeductionValue;
    }

    public void setVatDeductionValue(Double vatDeductionValue) {
        this.vatDeductionValue = vatDeductionValue;
    }
}
