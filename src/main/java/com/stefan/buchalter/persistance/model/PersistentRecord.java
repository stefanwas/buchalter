package com.stefan.buchalter.persistance.model;

public class PersistentRecord {

    public enum Type {
        VAT, PIT;
    }

    private Long id;
    private String code;
    private String date;
    private Type type;
    private String title;
    private Double pitValue;
    private Double netValue;
    private String vatRate;
    private Double vatValue;
    private Double grossValue;
    private Double vatDeductionRate;
    private Double vatDeductionValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPitValue() {
        return pitValue;
    }

    public void setPitValue(Double pitValue) {
        this.pitValue = pitValue;
    }

    public Double getNetValue() {
        return netValue;
    }

    public void setNetValue(Double netValue) {
        this.netValue = netValue;
    }

    public String getVatRate() {
        return vatRate;
    }

    public void setVatRate(String vatRate) {
        this.vatRate = vatRate;
    }

    public Double getVatValue() {
        return vatValue;
    }

    public void setVatValue(Double vatValue) {
        this.vatValue = vatValue;
    }

    public Double getGrossValue() {
        return grossValue;
    }

    public void setGrossValue(Double grossValue) {
        this.grossValue = grossValue;
    }

    public Double getVatDeductionRate() {
        return vatDeductionRate;
    }

    public void setVatDeductionRate(Double vatDeductionRate) {
        this.vatDeductionRate = vatDeductionRate;
    }

    public Double getVatDeductionValue() {
        return vatDeductionValue;
    }

    public void setVatDeductionValue(Double vatDeductionValue) {
        this.vatDeductionValue = vatDeductionValue;
    }
}
